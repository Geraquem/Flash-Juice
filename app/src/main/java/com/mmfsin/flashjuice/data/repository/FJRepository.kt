package com.mmfsin.flashjuice.data.repository

import android.util.Log
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.mmfsin.flashjuice.base.BaseDialog
import com.mmfsin.flashjuice.data.mappers.toRecordDTOList
import com.mmfsin.flashjuice.data.mappers.toRecordList
import com.mmfsin.flashjuice.data.models.RecordDTO
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
        val records = mutableListOf<RecordDTO>()
        val root = reference.child(RECORDS_ROOT)
        root.get().addOnCompleteListener { dataSnapshot ->
            for (child in dataSnapshot.result.children) {
                if (child.exists()) {
                    try {
                        child.getValue(RecordDTO::class.java)?.let { record ->
                            records.add(record)
                        }
                    } catch (e: Exception) {
                        Log.e(FJRepository::class.java.name, e.stackTraceToString())
                    }
                }
            }
            latch.countDown()
        }.addOnFailureListener { latch.countDown() }

        withContext(Dispatchers.IO) {
            latch.await()
        }
        return records.toRecordList().sortedBy { it.record }.reversed()
    }

    override suspend fun setNewWorldRecords(records: List<Record>) {
        val latch = CountDownLatch(1)
        val root = reference.child(RECORDS_ROOT)
        root.setValue(records.toRecordDTOList()).addOnCompleteListener {
            latch.countDown()
        }
        withContext(Dispatchers.IO) {
            latch.await()
        }
    }
}
