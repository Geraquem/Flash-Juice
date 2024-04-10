package com.mmfsin.flashjuice.domain.usecases

import android.content.Context
import com.mmfsin.flashjuice.base.BaseUseCase
import com.mmfsin.flashjuice.utils.MY_RECORD
import com.mmfsin.flashjuice.utils.RECORD
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class CheckIfRecordUseCase @Inject constructor(
    @ApplicationContext val context: Context
) : BaseUseCase<CheckIfRecordUseCase.Params, Boolean>() {

    override suspend fun execute(params: Params): Boolean {
        val mySharedPrefs = context.getSharedPreferences(MY_RECORD, Context.MODE_PRIVATE)
        val previousRecord = mySharedPrefs.getLong(RECORD, 1)
        return if (params.level > previousRecord) {
            mySharedPrefs.edit().apply() {
                putLong(RECORD, params.level)
                apply()
            }
            true
        } else false
    }

    data class Params(
        val level: Long
    )
}