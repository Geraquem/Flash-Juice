package com.mmfsin.flashjuice.base

abstract class BaseUseCaseNoParams<T> {
    abstract suspend fun execute(): T
}