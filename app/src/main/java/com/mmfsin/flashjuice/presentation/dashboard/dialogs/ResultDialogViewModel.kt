package com.mmfsin.flashjuice.presentation.dashboard.dialogs

import com.mmfsin.flashjuice.base.BaseViewModel
import com.mmfsin.flashjuice.domain.usecases.GetMyRecordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ResultDialogViewModel @Inject constructor(
    private val getMyRecordUseCase: GetMyRecordUseCase
) : BaseViewModel<ResultDialogEvent>() {

    fun getMyRecord() {
        executeUseCase(
            { getMyRecordUseCase.execute() },
            { result -> _event.value = ResultDialogEvent.GetMyRecord(result) },
            { _event.value = ResultDialogEvent.SWW },
        )
    }
}