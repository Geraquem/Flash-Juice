package com.mmfsin.flashjuice.domain.usecases

import com.mmfsin.flashjuice.base.BaseUseCase
import com.mmfsin.flashjuice.domain.models.Positions
import javax.inject.Inject

class GetPositionsUseCase @Inject constructor() :
    BaseUseCase<GetPositionsUseCase.Params, Positions>() {

    override suspend fun execute(params: Params): Positions {
        val positions = (0..19).toList()

        val juices = linkedSetOf<Int>()
        var poisons1: List<Int>
        var poisons2 = listOf<Int>()
        var poisons3 = listOf<Int>()
        var poisons4 = listOf<Int>()
        val duration: Long

        when (params.level) {
            in (1..2) -> {
                poisons1 = randomList(positions, 20)
                duration = 700
            }

            in (3..4) -> {
                duration = 650
                poisons1 = randomList(positions, 15)
                poisons2 = randomList(positions - poisons1.toSet(), 5)
            }

            in (5..6) -> {
                duration = 550
                poisons1 = randomList(positions, 10)
                poisons2 = randomList(positions - poisons1.toSet(), 10)
            }

            in (7..8) -> {
                poisons1 = randomList(positions, 10)
                poisons2 = randomList(positions - poisons1.toSet(), 7)
                poisons3 = randomList(positions - poisons1.toSet() - poisons2.toSet(), 3)
                duration = 500
            }

            in (9..10) -> {
                poisons1 = randomList(positions, 10)
                poisons2 = randomList(positions - poisons1.toSet(), 10)
                poisons3 = randomList(positions - poisons1.toSet() - poisons2.toSet(), 10)
                duration = 400
            }

            in (11..12) -> {
                poisons1 = randomList(positions, 9)
                poisons2 = randomList(positions - poisons1.toSet(), 6)
                poisons3 = randomList(positions - poisons1.toSet() - poisons2.toSet(), 3)
                poisons4 = positions - poisons1.toSet() - poisons2.toSet() - poisons3.toSet() //2
                duration = 250
            }

            in (13..14) -> {
                poisons1 = randomList(positions, 5)
                poisons2 = randomList(positions - poisons1.toSet(), 5)
                poisons3 = randomList(positions - poisons1.toSet() - poisons2.toSet(), 5)
                poisons4 = positions - poisons1.toSet() - poisons2.toSet() - poisons3.toSet()
                duration = 200
            }

            in (15..16) -> {
                poisons1 = randomList(positions, 5)
                poisons2 = randomList(positions - poisons1.toSet(), 5)
                poisons3 = randomList(positions - poisons1.toSet() - poisons2.toSet(), 5)
                poisons4 = positions - poisons1.toSet() - poisons2.toSet() - poisons3.toSet()
                duration = 150
            }

            else -> {
                poisons1 = randomList(positions, 5)
                poisons2 = randomList(positions - poisons1.toSet(), 5)
                poisons3 = randomList(positions - poisons1.toSet() - poisons2.toSet(), 5)
                poisons4 = positions - poisons1.toSet() - poisons2.toSet() - poisons3.toSet()
                duration = 140
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
        val level: Long
    )
}