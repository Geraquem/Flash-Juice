package com.mmfsin.flashjuice.presentation.dashboard

sealed class DashboardEvent {
    class ImageHeight(val height: Int) : DashboardEvent()
    object SomethingWentWrong : DashboardEvent()
}