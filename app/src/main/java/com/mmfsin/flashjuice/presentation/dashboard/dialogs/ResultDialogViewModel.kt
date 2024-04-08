package com.mmfsin.flashjuice.presentation.dashboard.dialogs

import com.mmfsin.flashjuice.base.BaseViewModel
import com.mmfsin.flashjuice.domain.usecases.CheckIfRecordUseCase
import com.mmfsin.flashjuice.domain.usecases.GetBadPhraseUseCase
import com.mmfsin.flashjuice.domain.usecases.GetGoodPhraseUseCase
import com.mmfsin.flashjuice.domain.usecases.GetMyRecordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ResultDialogViewModel @Inject constructor(
    private val getGoodPhraseUseCase: GetGoodPhraseUseCase,
    private val getBadPhraseUseCase: GetBadPhraseUseCase,
    private val getMyRecordUseCase: GetMyRecordUseCase,
    private val checkIfRecordUseCase: CheckIfRecordUseCase
) : BaseViewModel<ResultDialogEvent>() {

    fun getGoodPhrase() {
        executeUseCase(
            { getGoodPhraseUseCase.execute() },
            { result -> _event.value = ResultDialogEvent.GetPhrase(result) },
            { _event.value = ResultDialogEvent.SWW },
        )
    }

    fun getBadPhrase() {
        executeUseCase(
            { getBadPhraseUseCase.execute() },
            { result -> _event.value = ResultDialogEvent.GetPhrase(result) },
            { _event.value = ResultDialogEvent.SWW },
        )
    }

    fun getMyRecord() {
        executeUseCase(
            { getMyRecordUseCase.execute() },
            { result -> _event.value = ResultDialogEvent.GetMyRecord(result) },
            { _event.value = ResultDialogEvent.SWW },
        )
    }

    fun checkIfNewRecord(level: Int) {
        executeUseCase(
            { checkIfRecordUseCase.execute(CheckIfRecordUseCase.Params(level)) },
            { result -> _event.value = ResultDialogEvent.CheckIfNewRecord(result) },
            { _event.value = ResultDialogEvent.SWW },
        )
    }
}