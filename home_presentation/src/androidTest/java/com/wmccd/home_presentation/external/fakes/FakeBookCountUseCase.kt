package com.wmccd.home_presentation.external.fakes

import com.wmccd.book_domain.external.usecases.BookCountUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeBookCountUseCase: BookCountUseCase {
    override suspend fun execute(): Flow<Int> = flow{ emit(763)}
}