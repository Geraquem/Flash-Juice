package com.mmfsin.flashjuice.domain.usecases

import android.content.Context
import com.mmfsin.flashjuice.base.BaseUseCaseNoParams
import com.mmfsin.flashjuice.utils.MY_RECORD
import com.mmfsin.flashjuice.utils.RECORD
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class GetMyRecordUseCase @Inject constructor(
    @ApplicationContext val context: Context
) : BaseUseCaseNoParams<Long>() {

    override suspend fun execute(): Long {
        val mySharedPrefs = context.getSharedPreferences(MY_RECORD, Context.MODE_PRIVATE)
        return mySharedPrefs.getLong(RECORD, 0)
    }
}