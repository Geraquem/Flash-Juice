package com.mmfsin.flashjuice.domain.usecases

import com.mmfsin.flashjuice.base.BaseUseCaseNoParams
import com.mmfsin.flashjuice.domain.interfaces.IFJRepository
import javax.inject.Inject

class GetMyRecordUseCase @Inject constructor(
) : BaseUseCaseNoParams<Long>() {

    override suspend fun execute(): Long {

        return 150
    }
}