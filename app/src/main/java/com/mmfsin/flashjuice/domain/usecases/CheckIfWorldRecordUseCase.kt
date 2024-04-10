package com.mmfsin.flashjuice.domain.usecases

import com.mmfsin.flashjuice.base.BaseUseCase
import com.mmfsin.flashjuice.data.repository.FJRepository
import javax.inject.Inject

class CheckIfWorldRecordUseCase @Inject constructor(
    private val repository: FJRepository
) : BaseUseCase<CheckIfWorldRecordUseCase.Params, Boolean>() {

    override suspend fun execute(params: Params): Boolean {
        if (params.level > 30) {
            val records = repository.getRecords()
            records.forEach { record ->
                if (params.level > record.record) {
                    return true
                }
            }
            return false
        } else return false
    }

    data class Params(
        val level: Long
    )
}