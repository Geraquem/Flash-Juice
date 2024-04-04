package com.mmfsin.flashjuice.domain.models

data class Positions(
    val juices: List<Int>,
    val poisons1: List<Int>,
    val poisons2: List<Int>? = null,
    val poisons3: List<Int>? = null,
    val poisons4: List<Int>? = null,
    val duration: Long,
)
