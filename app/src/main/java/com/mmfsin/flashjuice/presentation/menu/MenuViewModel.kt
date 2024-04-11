package com.mmfsin.flashjuice.presentation.menu

import com.mmfsin.flashjuice.base.BaseViewModel
import com.mmfsin.flashjuice.domain.usecases.GetMyRecordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val getMyRecordUseCase: GetMyRecordUseCase,
) : BaseViewModel<MenuEvent>() {

    fun getMyRecord() {
        executeUseCase(
            { getMyRecordUseCase.execute() },
            { result -> _event.value = MenuEvent.GetMyRecord(result) },
            { _event.value = MenuEvent.SWW },
        )
    }
}