package com.mmfsin.flashjuice.presentation.ranking

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mmfsin.flashjuice.base.BaseFragment
import com.mmfsin.flashjuice.databinding.FragmentRankingBinding
import com.mmfsin.flashjuice.domain.models.Record
import com.mmfsin.flashjuice.presentation.ranking.adapter.RecordsAdapter
import com.mmfsin.flashjuice.utils.showErrorDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RankingFragment : BaseFragment<FragmentRankingBinding, RankingViewModel>() {

    override val viewModel: RankingViewModel by viewModels()
    private lateinit var mContext: Context

    override fun inflateView(
        inflater: LayoutInflater, container: ViewGroup?
    ) = FragmentRankingBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getRecords()
    }

    override fun setUI() {
        binding.apply {
            loading.visibility = View.VISIBLE
        }
    }

    override fun observe() {
        viewModel.event.observe(this) { event ->
            when (event) {
                is RankingEvent.GetRecords -> setUpRecords(event.records)
                is RankingEvent.SWW -> error()
            }
        }
    }

    private fun setUpRecords(records: List<Record>) {
        binding.apply {
            rvRecords.apply {
                layoutManager = LinearLayoutManager(mContext)
                adapter = RecordsAdapter(records)
            }
            loading.visibility = View.GONE
        }
    }

    private fun error() =
        activity?.let { it.showErrorDialog { it.onBackPressedDispatcher.onBackPressed() } }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }
}