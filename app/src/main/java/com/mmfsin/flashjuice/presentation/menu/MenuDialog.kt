package com.mmfsin.flashjuice.presentation.menu

import android.app.Dialog
import android.view.KeyEvent.KEYCODE_BACK
import android.view.LayoutInflater
import com.mmfsin.flashjuice.base.BaseDialog
import com.mmfsin.flashjuice.databinding.DialogMenuBinding
import com.mmfsin.flashjuice.domain.models.Difficult
import com.mmfsin.flashjuice.domain.models.Difficult.HARD
import com.mmfsin.flashjuice.domain.models.Difficult.NORMAL

class MenuDialog(val difficult: (difficult: Difficult) -> Unit) :
    BaseDialog<DialogMenuBinding>() {

    override fun inflateView(inflater: LayoutInflater) = DialogMenuBinding.inflate(inflater)

    override fun setCustomViewDialog(dialog: Dialog) = bottomViewDialog(dialog)

    override fun setUI() {
        isCancelable = false

        /** delete */
//        setDifficult(NORMAL)
    }

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

    override fun onResume() {
        super.onResume()
        dialog?.setOnKeyListener { _, keyCode, _ ->
            if (keyCode == KEYCODE_BACK) {
                activity?.finish()
                true
            } else false
        }
    }
}