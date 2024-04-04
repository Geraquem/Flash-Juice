package com.mmfsin.flashjuice.domain.interfaces

interface IFJRepository {
    suspend fun getRecords(): List<String>
}