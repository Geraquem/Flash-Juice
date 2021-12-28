package com.mmfsin.flashjuice.ranking

import androidx.fragment.app.Fragment
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.mmfsin.flashjuice.ranking.model.RecordDTO

class RankingInteractor(private val listener: IRanking) : Fragment() {

    fun getRanking() {
        Firebase.database.reference.child("records").get().addOnSuccessListener {
            val records = mutableListOf<RecordDTO>()
            for (record in it.children) {
                records.add(RecordDTO(record.key, record.value as Long))
            }
            listener.resultOk(records)
        }.addOnFailureListener {
            listener.resultKo()
        }
    }

    interface IRanking {
        fun resultOk(records: List<RecordDTO>)
        fun resultKo()
    }
}
