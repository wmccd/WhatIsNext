package com.wmccd.home_presentation.external.fakes

import com.wmccd.book_domain.external.usecases.BookCountUseCase
import com.wmccd.record_domain.external.usescases.RecordCountUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeRecordCountUseCase: RecordCountUseCase {
    override suspend fun execute(): Flow<Int> = flow{ emit(765)}
}