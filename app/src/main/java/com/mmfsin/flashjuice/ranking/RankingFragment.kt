package com.mmfsin.flashjuice.ranking

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mmfsin.flashjuice.R
import com.mmfsin.flashjuice.ranking.adapter.RViewAdapter
import com.mmfsin.flashjuice.ranking.model.RecordDTO
import kotlinx.android.synthetic.main.fragment_ranking.*

class RankingFragment : Fragment(), RankingView {

    private lateinit var mContext: Context

    private val presenter by lazy { RankingPresenter(this) }

    private lateinit var adapter: RViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ranking, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //loading visible
        presenter.getRanking()
    }

    override fun showLoading(visibility: Int) {
    }

    override fun fillRecyclerViewData(records: List<RecordDTO>) {
        recyclerView.layoutManager = LinearLayoutManager(mContext)
        adapter = RViewAdapter(records)
        recyclerView.adapter = adapter
//        loading gone
    }

    override fun showMessageError() {
        Toast.makeText(mContext, getString(R.string.somethingWentWrong), Toast.LENGTH_SHORT).show()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }
}