package com.mmfsin.flashjuice.data.mappers

import com.mmfsin.flashjuice.data.models.RecordDTO
import com.mmfsin.flashjuice.domain.models.Record

fun Record.toRecordDTO() = RecordDTO(
    name = name,
    record = record
)

fun List<Record>.toRecordDTOList() = this.map { record -> record.toRecordDTO() }.toList()