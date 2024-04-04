package com.mmfsin.flashjuice.presentation.menu

import android.app.Dialog
import android.view.LayoutInflater
import com.mmfsin.flashjuice.base.BaseDialog
import com.mmfsin.flashjuice.databinding.DialogMenuBinding
import com.mmfsin.flashjuice.domain.models.Difficult
import com.mmfsin.flashjuice.domain.models.Difficult.*

class MenuDialog(val difficult: (difficult: Difficult) -> Unit) :
    BaseDialog<DialogMenuBinding>() {

    override fun inflateView(inflater: LayoutInflater) = DialogMenuBinding.inflate(inflater)

    override fun setCustomViewDialog(dialog: Dialog) = bottomViewDialog(dialog)

    override fun setListeners() {
        binding.apply {
            btnNormal.setOnClickListener { setDifficult(NORMAL) }
            btnHard.setOnClickListener { setDifficult(HARD) }
        }
    }

    private fun setDifficult(difficult: Difficult) {
        difficult(difficult)
        dismiss()
    }
}