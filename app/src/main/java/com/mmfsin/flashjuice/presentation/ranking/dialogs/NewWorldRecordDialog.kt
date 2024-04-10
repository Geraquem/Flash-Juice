package com.mmfsin.flashjuice.presentation.ranking.dialogs

import android.app.Dialog
import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import com.mmfsin.flashjuice.base.BaseDialog
import com.mmfsin.flashjuice.databinding.DialogNewWorldRecordBinding
import com.mmfsin.flashjuice.presentation.dashboard.dialogs.ResultDialogViewModel

class NewWorldRecordDialog(val newRecord: Long) : BaseDialog<DialogNewWorldRecordBinding>() {

    private val viewModel: ResultDialogViewModel by viewModels()

    override fun inflateView(inflater: LayoutInflater) =
        DialogNewWorldRecordBinding.inflate(inflater)

    override fun setCustomViewDialog(dialog: Dialog) = bottomViewDialog(dialog)

    override fun setUI() {
        isCancelable = false

    }

    override fun setListeners() {
        binding.apply {
        }
    }
}