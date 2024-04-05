package com.mmfsin.flashjuice.domain.usecases

import com.mmfsin.flashjuice.base.BaseUseCaseNoParams
import javax.inject.Inject

class GetBadPhraseUseCase @Inject constructor() :
    BaseUseCaseNoParams<String>() {

    override suspend fun execute(): String {
        return "adadasdsafs"
    }
}