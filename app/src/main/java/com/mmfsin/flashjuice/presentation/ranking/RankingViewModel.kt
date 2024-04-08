package com.mmfsin.flashjuice.presentation.ranking

import com.mmfsin.flashjuice.base.BaseViewModel
import com.mmfsin.flashjuice.databinding.IncludeDashboardBinding
import com.mmfsin.flashjuice.domain.usecases.GetRecordsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RankingViewModel @Inject constructor(
    private val getRecordsUseCase: GetRecordsUseCase
) : BaseViewModel<RankingEvent>() {

    fun getRecords() {
        executeUseCase(
            { getRecordsUseCase.execute() },
            { result -> _event.value = RankingEvent.GetRecords(result) },
            { _event.value = RankingEvent.SWW },
        )
    }
}