package com.mmfsin.flashjuice.presentation.dashboard

import com.mmfsin.flashjuice.base.BaseViewModel
import com.mmfsin.flashjuice.domain.usecases.GetJuicesPositionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val getJuicesPositionsUseCase: GetJuicesPositionsUseCase
) : BaseViewModel<DashboardEvent>() {

    fun getJuices() {
        executeUseCase(
            { getJuicesPositionsUseCase.execute() },
            { },
            { }
        )
    }
}