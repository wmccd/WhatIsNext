package com.wmccd.record_domain.external.usescases

import com.wmccd.common_models_types.external.models.records.RecordModel

interface UpdateRecordUseCase {
    suspend fun execute(recordModel: RecordModel): Int
}

