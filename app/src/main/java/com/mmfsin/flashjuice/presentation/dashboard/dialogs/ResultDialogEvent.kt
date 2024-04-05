package com.mmfsin.flashjuice.presentation.dashboard.dialogs

sealed class ResultDialogEvent {
    class GetMyRecord(val record: Long) : ResultDialogEvent()
    object SWW : ResultDialogEvent()
}