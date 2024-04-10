package com.mmfsin.flashjuice.presentation.dashboard

import com.mmfsin.flashjuice.base.BaseViewModel
import com.mmfsin.flashjuice.databinding.IncludeDashboardBinding
import com.mmfsin.flashjuice.domain.usecases.GetImagesUseCase
import com.mmfsin.flashjuice.domain.usecases.GetPositionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val getImagesUseCase: GetImagesUseCase,
    private val getPositionsUseCase: GetPositionsUseCase
) : BaseViewModel<DashboardEvent>() {

    fun getImages(binding: IncludeDashboardBinding) {
        executeUseCase(
            { getImagesUseCase.execute(GetImagesUseCase.Params(binding)) },
            { result-> _event.value = DashboardEvent.GetImages(result)},
            { _event.value = DashboardEvent.SWW},
        )
    }
    fun getPositions(level: Long) {
        executeUseCase(
            { getPositionsUseCase.execute(GetPositionsUseCase.Params(level)) },
            { result-> _event.value = DashboardEvent.GetPositions(result)},
            { _event.value = DashboardEvent.SWW},
        )
    }
}