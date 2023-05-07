package com.wmccd.book_domain.external.usecases

import com.wmccd.analogue_reporter.external.AnalogueAction
import com.wmccd.analogue_reporter.external.AnalogueReporter
import com.wmccd.book_repository.external.BookRepository
import com.wmccd.common_models_types.external.models.books.BookModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AllBooksUseCaseImpl(
    private val bookRepository: BookRepository,
    private val analogueReporter: AnalogueReporter,
    private val dispatcher : CoroutineDispatcher = Dispatchers.Default
): AllBooksUseCase {

    //override suspend fun execute(): Flow<List<BookModel>> = bookRepository.books

    override suspend fun execute(): Flow<List<BookModel>> = bookRepository.books.map {
        analogueReporter.report(
            action = AnalogueAction.Trace(
                tag = "${this::class.simpleName}",
                whatHappened = "Domain: book flow updates",
            )
        )
        it
    }
}