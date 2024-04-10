package com.mmfsin.flashjuice.domain.interfaces

import com.mmfsin.flashjuice.domain.models.Record

interface IFJRepository {
    suspend fun getRecords(): List<Record>
    suspend fun setNewWorldRecords(records: List<Record>)
}