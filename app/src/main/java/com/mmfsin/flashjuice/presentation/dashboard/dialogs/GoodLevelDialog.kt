package com.mmfsin.flashjuice.presentation.dashboard.dialogs

import android.app.Dialog
import android.view.LayoutInflater
import com.mmfsin.flashjuice.base.BaseDialog
import com.mmfsin.flashjuice.databinding.DialogEndGameBinding
import com.mmfsin.flashjuice.domain.models.GameEndData
import com.mmfsin.flashjuice.presentation.dashboard.listeners.IGameEndListener

class GoodLevelDialog(private val data: GameEndData, private val listener: IGameEndListener) :
    BaseDialog<DialogEndGameBinding>() {

    override fun inflateView(inflater: LayoutInflater) = DialogEndGameBinding.inflate(inflater)

    override fun setCustomViewDialog(dialog: Dialog) = bottomViewDialog(dialog)

    override fun setUI() {
        isCancelable = false
        binding.apply { }
    }

    override fun setListeners() {
        binding.apply { }
    }
}