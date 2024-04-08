package com.mmfsin.flashjuice.presentation.ranking

import com.mmfsin.flashjuice.domain.models.Record

sealed class RankingEvent {
    class GetRecords(val records: List<Record>) : RankingEvent()
    object SWW : RankingEvent()
}