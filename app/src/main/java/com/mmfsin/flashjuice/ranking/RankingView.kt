package com.mmfsin.flashjuice.ranking

import com.mmfsin.flashjuice.ranking.model.RecordDTO

interface RankingView {
    fun showLoading(visibility: Int)
    fun fillRecyclerViewData(records: List<RecordDTO>)
    fun showMessageError()
}