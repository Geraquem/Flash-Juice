package com.mmfsin.flashjuice.domain.usecases

import com.mmfsin.flashjuice.base.BaseUseCaseNoParams
import com.mmfsin.flashjuice.domain.interfaces.IFJRepository
import javax.inject.Inject

class GetJuicesPositionsUseCase @Inject constructor(
    private val repository: IFJRepository
) : BaseUseCaseNoParams<List<String>>() {

    override suspend fun execute(): List<String> {
        val list = linkedSetOf<Int>()
        while (list.size < 5) {
            list.add((0..19).random())
        }
        return emptyList()
    }
}