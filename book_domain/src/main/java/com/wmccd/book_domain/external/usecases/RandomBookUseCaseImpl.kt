package com.wmccd.book_domain.external.usecases

import com.wmccd.analogue_reporter.external.AnalogueAction
import com.wmccd.analogue_reporter.external.AnalogueReporter
import com.wmccd.book_repository.external.BookRepository
import com.wmccd.common_models_types.external.models.books.BookModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first

class RandomBookUseCaseImpl(
    private val bookRepository: BookRepository,
    private val analogueReporter: AnalogueReporter,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default
):RandomBookUseCase {
    override suspend fun execute(): BookModel {

        val count = bookRepository.count.first()
        try {
            val random = (0 until count).random()
            analogueReporter.report(
                action = AnalogueAction.Trace(
                    tag = "${this::class.simpleName}",
                    whatHappened = "Domain: random book",
                    key = "index",
                    value = random
                )
            )
            return bookRepository.books.first()[random]
        }catch(ex:Exception){
            throw Exception("${RandomBookUseCase::class.simpleName} | ${ex.message}")
        }
    }
}