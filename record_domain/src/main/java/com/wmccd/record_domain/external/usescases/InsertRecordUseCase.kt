package com.wmccd.record_domain.external.usescases

import com.wmccd.common_models.external.records.RecordModel

interface InsertRecordUseCase {
    suspend fun execute(recordModel: RecordModel)
}

