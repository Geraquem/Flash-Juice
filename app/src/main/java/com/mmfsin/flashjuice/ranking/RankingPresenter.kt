package com.mmfsin.flashjuice.ranking

import com.mmfsin.flashjuice.ranking.model.RecordDTO

class RankingPresenter(val view: RankingView) : RankingInteractor.IRanking {

    private val interactor by lazy { RankingInteractor(this) }

    fun getRanking() {
        interactor.getRanking()
    }

    override fun resultOk(records: List<RecordDTO>) {
        view.fillRecyclerViewData(records)
    }

    override fun resultKo() {
        view.showMessageError()
    }

}