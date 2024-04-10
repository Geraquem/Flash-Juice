package com.mmfsin.flashjuice.presentation.ranking.dialogs

sealed class NewWorldRecordDialogEvent {
    object Completed : NewWorldRecordDialogEvent()
    object SWW : NewWorldRecordDialogEvent()
}