package com.wmccd.book_domain.external.usecases

import com.wmccd.analogue_reporter.external.AnalogueAction
import com.wmccd.analogue_reporter.external.AnalogueReporter
import com.wmccd.book_repository.external.BookRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class DeleteBookUseCaseImpl(
    private val bookRepository: BookRepository,
    private val analogueReporter: AnalogueReporter,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default
): DeleteBookUseCase {
    override suspend fun execute(id: Long) {
        analogueReporter.report(
            action = AnalogueAction.Trace(
                tag = "${this::class.simpleName}",
                whatHappened = "Domain: book delete",
                key = "id",
                value = id
            )
        )
        bookRepository.delete(id = id)
    }


}