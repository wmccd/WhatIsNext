package com.wmccd.record_domain.external.usescases

import com.wmccd.common_models.external.records.RecordModel
import kotlinx.coroutines.flow.Flow

interface AllRecordsUseCase {
    suspend fun execute(): Flow<List<RecordModel>>
}

