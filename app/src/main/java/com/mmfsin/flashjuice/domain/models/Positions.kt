package com.mmfsin.flashjuice.domain.models

data class Positions(
    val juices: List<Int>,
    val poison1: List<Int>? = null,
    val poison2: List<Int>? = null,
    val poison3: List<Int>? = null,
    val poison4: List<Int>? = null,
    val duration: Long,
)
