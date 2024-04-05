package com.mmfsin.flashjuice.presentation.dashboard.dialogs

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.fragment.app.viewModels
import com.mmfsin.flashjuice.R
import com.mmfsin.flashjuice.base.BaseDialog
import com.mmfsin.flashjuice.databinding.DialogEndGameBinding
import com.mmfsin.flashjuice.presentation.dashboard.listeners.IGameEndListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EndGameDialog(
    private val level: Int,
    private val juicesSuccess: Int,
    private val listener: IGameEndListener
) : BaseDialog<DialogEndGameBinding>() {

    private val viewModel: ResultDialogViewModel by viewModels()

    override fun inflateView(inflater: LayoutInflater) = DialogEndGameBinding.inflate(inflater)

    override fun setCustomViewDialog(dialog: Dialog) = bottomViewDialog(dialog)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observe()
        viewModel.getBadPhrase()
    }

    override fun setUI() {
        isCancelable = false
        binding.apply {
            tvLevelFailed.text = level.toString()
            val result = if (juicesSuccess == 0) R.string.end_game_none_juices
            else R.string.end_game_not_all_juices
            tvResult.text = getString(result)
            setJuicesImages()
        }
    }

    override fun setListeners() {
        binding.apply {
            btnStartAgain.setOnClickListener {
                listener.restart()
                dismiss()
            }
        }
    }

    private fun observe() {
        viewModel.event.observe(this) { event ->
            when (event) {
                is ResultDialogEvent.GetPhrase -> {
                    binding.tvPhrase.text = event.phrase
                    viewModel.getMyRecord()
                }

                is ResultDialogEvent.GetMyRecord -> {
                    binding.tvLevelRecord.text = event.record.toString()
                }

                is ResultDialogEvent.SWW -> {}
            }
        }
    }

    private fun setJuicesImages() {
        binding.juicesResult.apply {
            when (juicesSuccess) {
                0 -> {}

                1 -> juice1.setJuice()

                2 -> {
                    juice1.setJuice()
                    juice2.setJuice()
                }

                3 -> {
                    juice1.setJuice()
                    juice2.setJuice()
                    juice3.setJuice()
                }

                else -> {
                    juice1.setJuice()
                    juice2.setJuice()
                    juice3.setJuice()
                    juice4.setJuice()
                }
            }
        }
    }

    private fun ImageView.setJuice() = this.setImageResource(R.drawable.ic_juice)
}