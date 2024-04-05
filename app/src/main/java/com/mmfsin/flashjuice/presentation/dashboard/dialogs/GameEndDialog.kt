package com.mmfsin.flashjuice.presentation.dashboard.dialogs

import android.app.Dialog
import android.view.LayoutInflater
import com.mmfsin.flashjuice.base.BaseDialog
import com.mmfsin.flashjuice.databinding.DialogGameEndBinding
import com.mmfsin.flashjuice.domain.models.GameEnd
import com.mmfsin.flashjuice.domain.models.GameEnd.LOOSER
import com.mmfsin.flashjuice.domain.models.GameEnd.WINNER
import com.mmfsin.flashjuice.presentation.dashboard.listeners.IGameEndListener

class GameEndDialog(val result: GameEnd, private val listener: IGameEndListener) :
    BaseDialog<DialogGameEndBinding>() {

    override fun inflateView(inflater: LayoutInflater) = DialogGameEndBinding.inflate(inflater)

    override fun setCustomViewDialog(dialog: Dialog) = bottomViewDialog(dialog)

    override fun setUI() {
        isCancelable = false
        binding.apply {
            tvResult.text = result.name
        }
    }

    override fun setListeners() {
        binding.apply {
            btnAction.setOnClickListener { resultAndDismiss() }
        }
    }

    private fun resultAndDismiss() {
        when (result) {
            WINNER -> listener.nextLevel()
            LOOSER -> listener.restart()
        }
        dismiss()
    }
}