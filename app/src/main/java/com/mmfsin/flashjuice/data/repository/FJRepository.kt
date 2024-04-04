package com.mmfsin.flashjuice.data.repository

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.mmfsin.flashjuice.domain.interfaces.IFJRepository
import javax.inject.Inject

class FJRepository @Inject constructor() : IFJRepository {

    private val reference = Firebase.database.reference

    override suspend fun getRecords(): List<String> {
//        val latch = CountDownLatch(1)
//        val root = reference.child("DILEMMAS")
//        root.get().addOnCompleteListener { dataSnapshot ->
//            for (child in dataSnapshot.result.children) {
//                if (child.exists()) {
//                    val textTop = child.child(TXT_TOP).value.toString()
//                    dilemmaList.add(data)
//                }
//            }
//            latch.countDown()
//        }.addOnFailureListener { latch.countDown() }
//
//        withContext(Dispatchers.IO)
//        {
//            latch.await()
//        }
        return emptyList()
    }
}
