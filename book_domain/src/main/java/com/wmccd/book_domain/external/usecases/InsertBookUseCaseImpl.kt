package com.wmccd.book_domain.external.usecases

import com.wmccd.analogue_reporter.external.AnalogueAction
import com.wmccd.analogue_reporter.external.AnalogueReporter
import com.wmccd.book_domain.internal.BookChecker
import com.wmccd.book_repository.external.BookRepository
import com.wmccd.common_models_types.external.models.books.BookModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class InsertBookUseCaseImpl(
    private val bookRepository: BookRepository,
    private val analogueReporter: AnalogueReporter,
    private val dispatcher : CoroutineDispatcher = Dispatchers.Default
): InsertBookUseCase {
    override suspend fun execute(bookModel: BookModel) {

        analogueReporter.report(
            action = AnalogueAction.Trace(
                tag = "${this::class.simpleName}",
                whatHappened = "Domain: book insert"
            )
        )

        BookChecker().check(bookModel = bookModel)

        bookRepository.insert(bookModel = bookModel)
    }




}