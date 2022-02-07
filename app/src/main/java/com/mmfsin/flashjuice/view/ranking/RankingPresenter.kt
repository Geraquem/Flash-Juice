package com.mmfsin.flashjuice.view.ranking

import com.mmfsin.flashjuice.repository.FirebaseRepo
import com.mmfsin.flashjuice.view.ranking.model.RecordDTO
import java.util.logging.Level

class RankingPresenter(val view: RankingView) : FirebaseRepo.IRanking {

    private val repository by lazy { FirebaseRepo(this) }

    fun getRanking() {
        repository.getRanking()
    }

    override fun returnRecords(records: List<RecordDTO>) {
        view.fillRecyclerViewData(records)
    }

    override fun resultKo() {
        view.showMessageError()
    }

    override fun returnTopLevels(level: Int, levels: List<Long>) {}
    override fun newRecordWrote() {}
}