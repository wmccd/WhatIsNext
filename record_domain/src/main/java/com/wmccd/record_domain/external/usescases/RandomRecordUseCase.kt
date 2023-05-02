package com.wmccd.record_domain.external.usescases

import com.wmccd.common_models_types.external.models.records.RecordModel

interface RandomRecordUseCase {
    suspend fun execute(): RecordModel
}

