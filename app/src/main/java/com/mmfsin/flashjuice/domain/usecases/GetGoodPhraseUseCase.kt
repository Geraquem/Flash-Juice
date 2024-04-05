package com.mmfsin.flashjuice.domain.usecases

import android.content.Context
import com.mmfsin.flashjuice.R
import com.mmfsin.flashjuice.base.BaseUseCaseNoParams
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class GetGoodPhraseUseCase @Inject constructor(
    @ApplicationContext val context: Context
) : BaseUseCaseNoParams<String>() {

    override suspend fun execute(): String {
        val goodPhrases = context.resources.getStringArray(R.array.goodPhrases).toList()
        return goodPhrases.shuffled().random()
    }
}