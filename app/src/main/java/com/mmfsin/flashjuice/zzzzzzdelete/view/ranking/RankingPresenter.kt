package com.mmfsin.flashjuice.zzzzzzdelete.view.ranking

import com.mmfsin.flashjuice.zzzzzzdelete.repository.FirebaseRepo
import com.mmfsin.flashjuice.zzzzzzdelete.view.ranking.model.RecordDTO
import java.util.logging.Level

class RankingPresenter(val view: RankingView) : FirebaseRepo.IRanking {

    private val repository by lazy { FirebaseRepo(this) }

    fun getRanking() {
        repository.getRanking()
    }

    override fun returnRecords(records: List<RecordDTO>) {
        view.fillRecyclerViewData(records)
    }

    override fun somethingWentWrong() {
        view.showMessageError()
    }

    override fun returnTopLevels(level: Int, levels: List<Long>) {}
    override fun newRecordWrote() {}
}