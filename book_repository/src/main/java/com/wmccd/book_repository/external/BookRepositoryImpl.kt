package com.wmccd.book_repository.external

import com.wmccd.analogue_reporter.external.AnalogueAction
import com.wmccd.analogue_reporter.external.AnalogueReporter
import com.wmccd.book_datasource.external.BookDataSource
import com.wmccd.common_models_types.external.models.books.BookModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class BookRepositoryImpl(
    private val dataSource: BookDataSource,
    private val analogueReporter: AnalogueReporter,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
): BookRepository {

    //override val books: Flow<List<BookModel>> = dataSource.books
    override val books: Flow<List<BookModel>> = dataSource.books.map{
        analogueReporter.report(
            action = AnalogueAction.Trace(
                tag = "${this::class.simpleName}",
                whatHappened = "Repository: books flow update",
            )
        )
        it
    }

    //override val count: Flow<Int> = dataSource.count
    override val count: Flow<Int> = dataSource.count.map{
        analogueReporter.report(
            action = AnalogueAction.Trace(
                tag = "${this::class.simpleName}",
                whatHappened = "Repository: books count flow update",
                key = "count",
                value = it
            )
        )
        it
    }

    override suspend fun insert(bookModel: BookModel) {
        analogueReporter.report(
            action = AnalogueAction.Trace(
                tag = "${this::class.simpleName}",
                whatHappened = "Repository: book insert",
                key = "model",
                value = bookModel
            )
        )

        withContext(dispatcher){
            dataSource.insert(bookModel = bookModel)
        }
    }

    override suspend fun update(bookModel: BookModel): Int {
        analogueReporter.report(
            action = AnalogueAction.Trace(
                tag = "${this::class.simpleName}",
                whatHappened = "Repository: book update",
                key = "model",
                value = bookModel
            )
        )
        var count = 0
        withContext(dispatcher){
            count = dataSource.update(bookModel = bookModel)
        }
        return count
    }

    override suspend fun delete(id: Long): Int {
        analogueReporter.report(
            action = AnalogueAction.Trace(
                tag = "${this::class.simpleName}",
                whatHappened = "Repository: book delete",
                key = "id",
                value = id
            )
        )
        var count = 0
        withContext(dispatcher){
            count = dataSource.delete(id = id)
        }
        return count
    }

}