package com.mmfsin.flashjuice.domain.usecases

import com.mmfsin.flashjuice.base.BaseUseCaseNoParams
import com.mmfsin.flashjuice.domain.interfaces.IFJRepository
import com.mmfsin.flashjuice.domain.models.Record
import javax.inject.Inject

class GetRecordsUseCase @Inject constructor(
    private val repository: IFJRepository
) : BaseUseCaseNoParams<List<Record>>() {

    override suspend fun execute(): List<Record> = repository.getRecords()
}