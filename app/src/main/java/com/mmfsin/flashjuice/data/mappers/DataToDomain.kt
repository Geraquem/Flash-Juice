package com.mmfsin.flashjuice.data.mappers

import com.mmfsin.flashjuice.data.models.RecordDTO
import com.mmfsin.flashjuice.domain.models.Record

fun RecordDTO.toRecord() = Record(
    name = name,
    record = record
)

fun List<RecordDTO>.toRecordList() = this.map { record -> record.toRecord() }.toList()