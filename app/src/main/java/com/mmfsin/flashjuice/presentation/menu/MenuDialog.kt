package com.mmfsin.flashjuice.presentation.menu

import android.app.Dialog
import android.os.Bundle
import android.view.KeyEvent.KEYCODE_BACK
import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import com.mmfsin.flashjuice.R
import com.mmfsin.flashjuice.base.BaseDialog
import com.mmfsin.flashjuice.databinding.DialogMenuBinding
import com.mmfsin.flashjuice.presentation.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuDialog(val startGame: () -> Unit) : BaseDialog<DialogMenuBinding>() {

    private val viewModel: MenuViewModel by viewModels()

    override fun inflateView(inflater: LayoutInflater) = DialogMenuBinding.inflate(inflater)

    override fun setCustomViewDialog(dialog: Dialog) = bottomViewDialog(dialog)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observe()
        viewModel.getMyRecord()
    }

    override fun setUI() {
        isCancelable = false
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

    private fun observe() {
        viewModel.event.observe(this) { event ->
            when (event) {
                is MenuEvent.GetMyRecord -> {
                    binding.tvMyRecord.text =
                        getString(R.string.menu_record_actual, event.record.toString())
                }

                is MenuEvent.SWW -> {}
            }
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