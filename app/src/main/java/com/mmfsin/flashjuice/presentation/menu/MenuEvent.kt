package com.mmfsin.flashjuice.presentation.menu

sealed class MenuEvent {
    class GetMyRecord(val record: Long) : MenuEvent()
    object SWW : MenuEvent()
}