package com.mmfsin.flashjuice.view.ranking

import com.mmfsin.flashjuice.view.ranking.model.RecordDTO

interface RankingView {
    fun fillRecyclerViewData(records: List<RecordDTO>)
    fun showMessageError()
}