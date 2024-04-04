package com.mmfsin.flashjuice.domain.usecases

import com.mmfsin.flashjuice.base.BaseUseCase
import com.mmfsin.flashjuice.domain.models.Positions
import javax.inject.Inject

class GetPositionsUseCase @Inject constructor() :
    BaseUseCase<GetPositionsUseCase.Params, Positions>() {

    override suspend fun execute(params: Params): Positions {
        val positions = (0..19).toList()

        val juices = linkedSetOf<Int>()
        var poisons1 = listOf<Int>()
        var poisons2 = listOf<Int>()
        var poisons3 = listOf<Int>()
        var poisons4 = listOf<Int>()
        val duration: Long

        when (params.level) {
            in (1..2) -> {
                poisons1 = randomList(positions, 5)
                poisons2 = randomList(positions - poisons1.toSet(), 5)
                poisons3 = randomList(positions - poisons1.toSet() - poisons2.toSet(), 5)
                poisons4 = positions - poisons1.toSet() - poisons2.toSet() - poisons3.toSet()

                duration = 1000
            }

            in (3..4) -> {
                duration = 850
            }

            in (5..6) -> {
                duration = 800
            }

            in (7..8) -> {
                duration = 750
            }

            in (9..11) -> {
                duration = 700
            }

            in (12..16) -> {
                duration = 630
            }

            in (17..18) -> {
                duration = 560
            }

            in (19..20) -> {
                duration = 450
            }

            in (20..21) -> {
                duration = 330
            }

            in (22..23) -> {
                duration = 250
            }

            in (23..24) -> {
                duration = 200
            }

            in (25..26) -> {
                duration = 160
            }

            in (27..29) -> {
                duration = 155
            }

            else -> {
                duration = 145
            }
        }

        while (juices.size < 5) {
            juices.add((0..19).random())
        }

        return Positions(
            juices = juices.toList(),
            poisons1 = poisons1,
            poisons2 = poisons2.ifEmpty { null },
            poisons3 = poisons3.ifEmpty { null },
            poisons4 = poisons4.ifEmpty { null },
            duration = duration
        )
    }

    private fun randomList(list: List<Int>, quantity: Int): List<Int> {
        return list.shuffled().take(quantity)
    }

    data class Params(
        val level: Int
    )
}