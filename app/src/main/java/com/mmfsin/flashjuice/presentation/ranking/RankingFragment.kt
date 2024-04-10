package com.mmfsin.flashjuice.presentation.ranking

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mmfsin.flashjuice.R
import com.mmfsin.flashjuice.base.BaseFragment
import com.mmfsin.flashjuice.base.BedRockActivity
import com.mmfsin.flashjuice.databinding.FragmentRankingBinding
import com.mmfsin.flashjuice.domain.models.Record
import com.mmfsin.flashjuice.presentation.dashboard.dialogs.GoodLevelDialog
import com.mmfsin.flashjuice.presentation.ranking.adapter.RecordsAdapter
import com.mmfsin.flashjuice.presentation.ranking.dialogs.NewWorldRecordDialog
import com.mmfsin.flashjuice.utils.ARGS_RECORD
import com.mmfsin.flashjuice.utils.countDown
import com.mmfsin.flashjuice.utils.showErrorDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RankingFragment : BaseFragment<FragmentRankingBinding, RankingViewModel>() {

    override val viewModel: RankingViewModel by viewModels()
    private lateinit var mContext: Context

    private var newRecord: Long? = null

    override fun inflateView(
        inflater: LayoutInflater, container: ViewGroup?
    ) = FragmentRankingBinding.inflate(inflater, container, false)

    override fun getBundleArgs() {
        newRecord = activity?.intent?.getLongExtra(ARGS_RECORD, -1)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getRecords()
    }

    override fun setUI() {
        binding.apply {
            setToolbar()
            checkIfNewRecord()
            clTop.visibility = View.INVISIBLE
            loading.visibility = View.VISIBLE
        }
    }

    private fun setToolbar() {
        (activity as BedRockActivity).apply {
            backListener { activity?.onBackPressedDispatcher?.onBackPressed() }
            setToolbarText(R.string.ranking_title)
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
                adapter = RecordsAdapter(records.takeLast(9))
            }
            if (records.isNotEmpty()) setWinner(records.first())
            loading.visibility = View.GONE
        }
    }

    private fun setWinner(first: Record) {
        binding.apply {
            tvWinnerName.text = first.name
            tvWinnerLevel.text = first.record.toString()
            clTop.visibility = View.VISIBLE
        }
    }

    private fun checkIfNewRecord() {
        newRecord?.let { record ->
            if (record.toInt() != -1) {
                val dialog = NewWorldRecordDialog(record) { viewModel.getRecords() }
                activity?.let {
                    countDown(200) { dialog.show(it.supportFragmentManager, "") }
                }
            }
        }
    }

    private fun error() =
        activity?.let { it.showErrorDialog { it.onBackPressedDispatcher.onBackPressed() } }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }
}