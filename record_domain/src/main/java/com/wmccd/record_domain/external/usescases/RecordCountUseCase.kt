package com.wmccd.record_domain.external.usescases

import kotlinx.coroutines.flow.Flow

interface RecordCountUseCase {
    suspend fun execute(): Flow<Int>
}