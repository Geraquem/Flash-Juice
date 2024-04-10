package com.mmfsin.flashjuice.presentation.menu

import android.app.Dialog
import android.view.KeyEvent.KEYCODE_BACK
import android.view.LayoutInflater
import com.mmfsin.flashjuice.base.BaseDialog
import com.mmfsin.flashjuice.databinding.DialogMenuBinding
import com.mmfsin.flashjuice.presentation.MainActivity

class MenuDialog(val startGame: () -> Unit) :
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
            btnStart.setOnClickListener {
                startGame()
                dismiss()
            }
            btnInstructions.setOnClickListener { (activity as MainActivity).openInstructions() }
            tvRanking.setOnClickListener { (activity as MainActivity).openRanking() }
        }
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