package com.mmfsin.flashjuice.presentation.dashboard.dialogs

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import com.mmfsin.flashjuice.R
import com.mmfsin.flashjuice.base.BaseDialog
import com.mmfsin.flashjuice.databinding.DialogEndGameBinding
import com.mmfsin.flashjuice.presentation.dashboard.listeners.IGameEndListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EndGameDialog(
    private val level: Int,
    val juicesSuccess: Int,
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
            tvLevelFailed.text = getString(R.string.end_game_last_level, level.toString())
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
                    binding.tvLevelRecord.text =
                        getString(R.string.end_game_record, event.record.toString())
                }

                is ResultDialogEvent.SWW -> {}
            }
        }
    }
}