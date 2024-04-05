package com.mmfsin.flashjuice.presentation.dashboard.dialogs

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import com.mmfsin.flashjuice.R
import com.mmfsin.flashjuice.base.BaseDialog
import com.mmfsin.flashjuice.databinding.DialogGoodLevelBinding
import com.mmfsin.flashjuice.presentation.dashboard.listeners.IGameEndListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GoodLevelDialog(private val level: Int, private val listener: IGameEndListener) :
    BaseDialog<DialogGoodLevelBinding>() {

    private val viewModel: ResultDialogViewModel by viewModels()

    override fun inflateView(inflater: LayoutInflater) = DialogGoodLevelBinding.inflate(inflater)

    override fun setCustomViewDialog(dialog: Dialog) = bottomViewDialog(dialog)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observe()
        viewModel.getGoodPhrase()
    }

    override fun setUI() {
        isCancelable = false
        binding.tvLevelSuccess.text = getString(R.string.good_level_last_level, level.toString())
    }

    override fun setListeners() {
        binding.apply {
            btnStartAgain.setOnClickListener {
                listener.nextLevel()
                dismiss()
            }
        }
    }

    private fun observe() {
        viewModel.event.observe(this) { event ->
            when (event) {
                is ResultDialogEvent.GetPhrase -> {
                    binding.tvPhrase.text = event.phrase
                }

                is ResultDialogEvent.GetMyRecord -> {}
                is ResultDialogEvent.SWW -> {}
            }
        }
    }
}