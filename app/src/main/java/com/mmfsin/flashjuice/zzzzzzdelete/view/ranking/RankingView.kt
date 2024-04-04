package com.mmfsin.flashjuice.zzzzzzdelete.view.ranking

import com.mmfsin.flashjuice.zzzzzzdelete.view.ranking.model.RecordDTO

interface RankingView {
    fun fillRecyclerViewData(records: List<RecordDTO>)
    fun showMessageError()
}