package com.mmfsin.flashjuice.repository

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.mmfsin.flashjuice.view.ranking.model.RecordDTO

class FirebaseRepo(private val listener: IRanking) {

    fun getRanking() {
        Firebase.database.reference.child("records").get().addOnSuccessListener { it ->
            val records = mutableListOf<RecordDTO>()
            for (record in it.children) {
                records.add(RecordDTO(record.key, record.value as Long))
            }
            listener.returnRecords(records.sortedByDescending { it.level })

        }.addOnFailureListener {
            listener.resultKo()
        }
    }

    fun getRankingLevels(level: Int) {
        Firebase.database.reference.child("records").get().addOnSuccessListener { it ->
            val levels = mutableListOf<Long>()
            for (record in it.children) {
                levels.add(record.value as Long)
            }
            listener.returnTopLevels(level, levels.sortedByDescending { it })

        }.addOnFailureListener {
            listener.resultKo()
        }
    }

    interface IRanking {
        fun returnRecords(records: List<RecordDTO>)
        fun returnTopLevels(level: Int, levels: List<Long>)
        fun resultKo()
    }
}