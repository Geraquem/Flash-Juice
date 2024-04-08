package com.mmfsin.flashjuice.data.repository

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.mmfsin.flashjuice.domain.interfaces.IFJRepository
import com.mmfsin.flashjuice.domain.models.Record
import com.mmfsin.flashjuice.utils.RECORDS_ROOT
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.concurrent.CountDownLatch
import javax.inject.Inject

class FJRepository @Inject constructor() : IFJRepository {

    private val reference = Firebase.database.reference

    override suspend fun getRecords(): List<Record> {
        val latch = CountDownLatch(1)
        val records = mutableListOf<Record>()
        val root = reference.child(RECORDS_ROOT)
        root.get().addOnCompleteListener { dataSnapshot ->
            for (child in dataSnapshot.result.children) {
                if (child.exists()) {
                    val a = 2

//                    records.add()
                }
            }
            latch.countDown()
        }.addOnFailureListener { latch.countDown() }

        withContext(Dispatchers.IO) {
            latch.await()
        }
        return records
    }
}
