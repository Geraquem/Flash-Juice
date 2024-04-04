package com.mmfsin.flashjuice.domain.usecases

import com.mmfsin.flashjuice.base.BaseUseCase
import com.mmfsin.flashjuice.domain.interfaces.IFJRepository
import com.mmfsin.flashjuice.domain.models.Positions
import javax.inject.Inject

class GetPositionsUseCase @Inject constructor(
    private val repository: IFJRepository
) : BaseUseCase<GetPositionsUseCase.Params, Positions>() {

    override suspend fun execute(params: Params): Positions {
        val juices = linkedSetOf<Int>()
        while (juices.size < 5) {
            juices.add((0..19).random())
        }

        when (params.level) {
            in (1..3) -> {

            }
        }

        return Positions(
            juices = juices.toList(),
            duration = 1000
        )
    }

    data class Params(
        val level: Int
    )
}