package com.mmfsin.flashjuice.presentation.dashboard.dialogs

sealed class ResultDialogEvent {
    class GetPhrase(val phrase: String) : ResultDialogEvent()
    class GetMyRecord(val record: Long) : ResultDialogEvent()
    class CheckIfNewRecord(val isNewRecord: Boolean) : ResultDialogEvent()
    class CheckIfNewWorldRecord(val worldRecord: Boolean) : ResultDialogEvent()
    object SWW : ResultDialogEvent()
}