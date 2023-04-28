package com.wmccd.book_domain.external.usecases

import kotlinx.coroutines.flow.Flow

interface BookCountUseCase {
    suspend fun execute(): Flow<Int>
}