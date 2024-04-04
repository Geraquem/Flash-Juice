package com.mmfsin.flashjuice.base.dialog

import android.app.Dialog
import android.view.LayoutInflater
import com.mmfsin.flashjuice.databinding.DialogErrorBinding
import com.mmfsin.flashjuice.base.BaseDialog

class ErrorDialog : BaseDialog<DialogErrorBinding>() {

    override fun inflateView(inflater: LayoutInflater) = DialogErrorBinding.inflate(inflater)

    override fun setCustomViewDialog(dialog: Dialog) = centerViewDialog(dialog)

    override fun setListeners() {
        binding.btnAccept.setOnClickListener {
            activity?.onBackPressedDispatcher?.onBackPressed()
            dismiss()
        }
    }
}