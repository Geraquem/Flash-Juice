package com.mmfsin.flashjuice.domain.usecases

import com.mmfsin.flashjuice.base.BaseUseCase
import com.mmfsin.flashjuice.domain.interfaces.IFJRepository
import javax.inject.Inject

class SetNewWorldRecordUseCase @Inject constructor(
    private val repository: IFJRepository
) : BaseUseCase<SetNewWorldRecordUseCase.Params, Unit>() {

    override suspend fun execute(params: Params) {
    }

    data class Params(
        val name: String,
        val newRecord: Long
    )
}