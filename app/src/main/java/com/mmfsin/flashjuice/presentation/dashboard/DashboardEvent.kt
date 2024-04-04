package com.mmfsin.flashjuice.presentation.dashboard

import android.widget.ImageView
import com.mmfsin.flashjuice.domain.models.Positions

sealed class DashboardEvent {
    class GetImages(val images: List<ImageView>) : DashboardEvent()
    class GetPositions(val positions: Positions) : DashboardEvent()
    object SWW : DashboardEvent()
}