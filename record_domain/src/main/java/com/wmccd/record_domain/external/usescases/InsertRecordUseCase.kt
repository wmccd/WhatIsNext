package com.wmccd.record_domain.external.usescases

import com.wmccd.common_models_types.external.models.records.RecordModel

interface InsertRecordUseCase {
    suspend fun execute(recordModel: RecordModel)
}

