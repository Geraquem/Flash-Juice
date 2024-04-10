package com.mmfsin.flashjuice.presentation.ranking.dialogs

import com.mmfsin.flashjuice.base.BaseViewModel
import com.mmfsin.flashjuice.domain.usecases.SetNewWorldRecordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewWorldRecordDialogViewModel @Inject constructor(
    private val setNewWorldRecordUseCase: SetNewWorldRecordUseCase
) : BaseViewModel<NewWorldRecordDialogEvent>() {

    fun getGoodPhrase(name: String, record: Long) {
        executeUseCase(
            {
                setNewWorldRecordUseCase.execute(
                    SetNewWorldRecordUseCase.Params(
                        name = name,
                        newRecord = record
                    )
                )
            },
            { _event.value = NewWorldRecordDialogEvent.Completed },
            { _event.value = NewWorldRecordDialogEvent.SWW },
        )
    }
}