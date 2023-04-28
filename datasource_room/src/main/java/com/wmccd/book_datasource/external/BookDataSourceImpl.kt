package com.wmccd.book_datasource.external

import android.content.Context
import com.wmccd.analogue_reporter.external.AnalogueAction
import com.wmccd.analogue_reporter.external.AnalogueReporter
import com.wmccd.book_datasource.internal.WhatIsNextDatabase
import com.wmccd.book_datasource.internal.converters.BookEntityAndModelConverter
import com.wmccd.common_models.external.books.BookModel

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BookDataSourceImpl(
    private val context: Context,
    private val analogueReporter: AnalogueReporter
): BookDataSource {

    private val bookStore = WhatIsNextDatabase.instance(context = context).bookStore()
    private val converter = BookEntityAndModelConverter()

    override val books: Flow<List<BookModel>> = bookStore.fetchAll().map { bookEntityList ->
        val modelList = arrayListOf<BookModel>()
        bookEntityList.forEach { modelList.add(
            converter.convert( bookEntity = it)
        ) }

        analogueReporter.report(
            action = AnalogueAction.Trace(
                tag = "${this::class.simpleName}",
                whatHappened = "DataSource: books flow update",
            )
        )
        modelList
    }

    override val count: Flow<Int> = bookStore.count().map {
        analogueReporter.report(
            action = AnalogueAction.Trace(
                tag = "${this::class.simpleName}",
                whatHappened = "DataSource: books count flow update",
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
                whatHappened = "DataSource: books insert",
                key = "model",
                value = bookModel
            )
        )
        bookStore.insert(
            converter.convert(bookModel = bookModel)
        )
    }

    override suspend fun update(bookModel: BookModel): Int {
        analogueReporter.report(
            action = AnalogueAction.Trace(
                tag = "${this::class.simpleName}",
                whatHappened = "DataSource: books update",
                key = "model",
                value = bookModel
            )
        )
        return bookStore.update(
            converter.convert(bookModel = bookModel)
        )
    }

    override suspend fun delete(id: Long): Int {
        analogueReporter.report(
            action = AnalogueAction.Trace(
                tag = "${this::class.simpleName}",
                whatHappened = "DataSource: books delete",
                key = "id",
                value = id
            )
        )
        return bookStore.delete( id = id)
    }
}