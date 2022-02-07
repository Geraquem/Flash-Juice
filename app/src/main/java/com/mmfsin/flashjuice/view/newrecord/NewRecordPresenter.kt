package com.mmfsin.flashjuice.view.newrecord

import com.mmfsin.flashjuice.repository.FirebaseRepo
import com.mmfsin.flashjuice.view.ranking.model.RecordDTO

class NewRecordPresenter(val view: NewRecordView) : FirebaseRepo.IRanking {

    private val repository by lazy { FirebaseRepo(this) }

    fun writeNewRecordFragment(userName: String, level: Int) {
        repository.setNewRecord(userName, level)
    }

    override fun newRecordWrote() {
        view.newRecordWrote()
    }

    override fun somethingWentWrong() {
        view.somethingWentWrong()
    }

    override fun returnRecords(records: List<RecordDTO>) {}
    override fun returnTopLevels(level: Int, levels: List<Long>) {}
}