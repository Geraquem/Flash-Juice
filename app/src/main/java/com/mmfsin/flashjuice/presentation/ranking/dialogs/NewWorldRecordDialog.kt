package com.mmfsin.flashjuice.presentation.ranking.dialogs

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import com.mmfsin.flashjuice.R
import com.mmfsin.flashjuice.base.BaseDialog
import com.mmfsin.flashjuice.databinding.DialogNewWorldRecordBinding
import com.mmfsin.flashjuice.presentation.dashboard.dialogs.ResultDialogEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewWorldRecordDialog(
    private val newRecord: Long,
    private val update: () -> Unit
) : BaseDialog<DialogNewWorldRecordBinding>() {

    private val viewModel: NewWorldRecordDialogViewModel by viewModels()

    override fun inflateView(inflater: LayoutInflater) =
        DialogNewWorldRecordBinding.inflate(inflater)

    override fun setCustomViewDialog(dialog: Dialog) = centerViewDialog(dialog)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observe()
    }

    override fun setUI() {
        isCancelable = false
        binding.apply {
            tvYourRecord.text = getString(R.string.new_world_record_record, newRecord.toString())
            tvError.visibility = View.GONE
            loading.visibility = View.GONE
        }
    }

    override fun setListeners() {
        binding.apply {
            btnClose.setOnClickListener { dismiss() }
            btnRegister.setOnClickListener {
                tvError.visibility = View.GONE
                val name = etName.text.toString()
                if (name.isBlank()) tvError.visibility = View.VISIBLE
                else {
                    loading.visibility = View.VISIBLE
                    btnRegister.visibility = View.GONE
                    viewModel.setNewWorldRecord(name, newRecord)
                }
            }
        }
    }

    private fun observe() {
        viewModel.event.observe(this) { event ->
            when (event) {
                is NewWorldRecordDialogEvent.Completed -> {
                    update()
                    dismiss()
                }

                is NewWorldRecordDialogEvent.SWW -> dismiss()
            }
        }
    }
}