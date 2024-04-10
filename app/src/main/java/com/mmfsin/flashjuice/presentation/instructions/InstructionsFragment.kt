package com.mmfsin.flashjuice.presentation.instructions

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.mmfsin.flashjuice.R
import com.mmfsin.flashjuice.base.BaseFragmentNoVM
import com.mmfsin.flashjuice.base.BedRockActivity
import com.mmfsin.flashjuice.databinding.FragmentInstructionsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InstructionsFragment : BaseFragmentNoVM<FragmentInstructionsBinding>() {

    private lateinit var mContext: Context

    override fun inflateView(
        inflater: LayoutInflater, container: ViewGroup?
    ) = FragmentInstructionsBinding.inflate(inflater, container, false)

    override fun setUI() {
        setToolbar()
    }

    private fun setToolbar() {
        (activity as BedRockActivity).apply {
            backListener { activity?.onBackPressedDispatcher?.onBackPressed() }
            setToolbarText(R.string.instructions_title)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }
}