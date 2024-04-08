package com.mmfsin.flashjuice.presentation.dashboard.dialogs

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.core.view.isVisible
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
    private val listener: IGameEndListener,
    private val timerZero: Boolean
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
            tvNewRecord.visibility = View.GONE
            tvLevel.text = level.toString()
            tvTimesUp.isVisible = timerZero
            val animation = if (timerZero) R.raw.times_up
            else R.raw.heart_broken
            lottieBadResult.setAnimation(animation)
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
                    binding.tvActualRecord.text = event.record.toString()
                    viewModel.checkIfNewRecord(level)
                }

                is ResultDialogEvent.CheckIfNewRecord -> setNewRecordData(event.isNewRecord)
                is ResultDialogEvent.SWW -> {}
            }
        }
    }

    private fun setNewRecordData(isNewRecord: Boolean) {
        binding.apply {
            tvPhrase.isVisible = !isNewRecord
            tvNewRecord.isVisible = isNewRecord
            if (isNewRecord) {
                tvRecord.text = getString(R.string.end_game_old_record)
                tvLevelFailed.text = getString(R.string.end_game_new_record)
                lottieBadResult.setAnimation(R.raw.trophy)
            } else {
                tvRecord.text = getString(R.string.end_game_record)
                tvLevelFailed.text = getString(R.string.end_game_last_level)
            }
            lottieBadResult.playAnimation()
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