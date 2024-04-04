package com.mmfsin.flashjuice.zzzzzzdelete.view.ranking

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mmfsin.flashjuice.IListener
import com.mmfsin.flashjuice.R
import com.mmfsin.flashjuice.zzzzzzdelete.view.ranking.adapter.RViewAdapter
import com.mmfsin.flashjuice.zzzzzzdelete.view.ranking.model.RecordDTO

class RankingFragment(private val listener: IListener) : Fragment(), RankingView {

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

        presenter.getRanking()
    }

    override fun fillRecyclerViewData(records: List<RecordDTO>) {

    }

    override fun showMessageError() {
        presenter.getRanking()
        listener.somethingWentWrong()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }
}

