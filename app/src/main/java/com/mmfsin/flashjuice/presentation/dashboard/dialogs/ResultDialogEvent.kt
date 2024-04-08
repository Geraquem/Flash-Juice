package com.mmfsin.flashjuice.presentation.dashboard.dialogs

sealed class ResultDialogEvent {
    class GetPhrase(val phrase: String) : ResultDialogEvent()
    class GetMyRecord(val record: Int) : ResultDialogEvent()
    class CheckIfNewRecord(val isNewRecord: Boolean) : ResultDialogEvent()
    object SWW : ResultDialogEvent()
}