package com.mmfsin.flashjuice.domain.usecases

import com.mmfsin.flashjuice.base.BaseUseCaseNoParams
import com.mmfsin.flashjuice.domain.interfaces.IFJRepository
import javax.inject.Inject

class GetRecordsUseCase @Inject constructor(
    private val repository: IFJRepository
) : BaseUseCaseNoParams<List<String>>() {

    override suspend fun execute(): List<String> = repository.getRecords()
}