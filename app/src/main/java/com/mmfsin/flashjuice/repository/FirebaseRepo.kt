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
            listener.somethingWentWrong()
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
            listener.somethingWentWrong()
        }
    }

    fun setNewRecord(userName: String, level: Int) {
        Firebase.database.reference.child("records").get().addOnSuccessListener { it ->
            val records = mutableListOf<RecordDTO>()
            for (record in it.children) {
                records.add(RecordDTO(record.key, record.value as Long))
            }
            val sortedRecords = records.sortedByDescending { it.level }
            writeNewRecord(userName, level, sortedRecords)
        }.addOnFailureListener {
            listener.somethingWentWrong()
        }
    }

    private fun writeNewRecord(userName: String, level: Int, records: List<RecordDTO>) {
        val reference = Firebase.database.reference.child("records")

        val lowestRecord = records.last()
        lowestRecord.name?.let { reference.child(it).removeValue() }
        reference.child(userName).setValue(level).addOnCompleteListener {
            when{
                it.isSuccessful -> listener.newRecordWrote()
                else -> listener.somethingWentWrong()
            }
        }
    }

    interface IRanking {
        fun returnRecords(records: List<RecordDTO>)
        fun returnTopLevels(level: Int, levels: List<Long>)
        fun newRecordWrote()
        fun somethingWentWrong()
    }
}