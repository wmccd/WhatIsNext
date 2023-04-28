package com.wmccd.book_domain.external.usecases

import com.wmccd.analogue_reporter.external.AnalogueAction
import com.wmccd.analogue_reporter.external.AnalogueReporter
import com.wmccd.book_domain.internal.BookChecker
import com.wmccd.common_models.external.books.BookModel
import com.wmccd.book_repository.external.BookRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class UpdateBookUseCaseImpl(
    private val bookRepository: BookRepository,
    private val analogueReporter: AnalogueReporter,
    private val dispatcher : CoroutineDispatcher = Dispatchers.Default
): UpdateBookUseCase {
    override suspend fun execute(bookModel: BookModel): Int {

        analogueReporter.report(
            action = AnalogueAction.Trace(
                tag = "${this::class.simpleName}",
                whatHappened = "Domain: book update",
                key = "",
                value = ""
            )
        )

        BookChecker().check(bookModel = bookModel)

        return bookRepository.update(bookModel = bookModel)
    }




}