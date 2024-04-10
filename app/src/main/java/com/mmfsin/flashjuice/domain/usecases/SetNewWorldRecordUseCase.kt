package com.mmfsin.flashjuice.domain.usecases

import com.mmfsin.flashjuice.base.BaseUseCase
import com.mmfsin.flashjuice.domain.interfaces.IFJRepository
import com.mmfsin.flashjuice.domain.models.Record
import javax.inject.Inject

class SetNewWorldRecordUseCase @Inject constructor(
    private val repository: IFJRepository
) : BaseUseCase<SetNewWorldRecordUseCase.Params, Unit>() {

    override suspend fun execute(params: Params) {
        val myRecord = Record(name = params.name, record = params.newRecord)

        val newRecords = repository.getRecords() as MutableList<Record>
        newRecords.add(myRecord)

        val sortedList = newRecords.sortedBy { it.record }.reversed().take(10)
        repository.setNewWorldRecords(sortedList)
    }

    data class Params(
        val name: String,
        val newRecord: Long
    )
}