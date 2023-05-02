package com.wmccd.book_repository.external.fakes

import com.wmccd.book_datasource.external.BookDataSource
import com.wmccd.common_models_types.external.models.books.BookModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeBookDataSource: BookDataSource {

    var lastInvoked = ""

    override val books: Flow<List<BookModel>> = flow {
        val list = listOf<BookModel>(
            BookModel(id = 1L, author = "A", title = "B"),
            BookModel(id = 2L, author = "C", title = "D"),

            )
        lastInvoked = "fetch all"
        emit(list)
    }

    override val count: Flow<Int> = flow{
        lastInvoked = "count"

        emit(77)}

    override suspend fun insert(bookModel: BookModel) {
        lastInvoked = "insert"

    }

    override suspend fun update(bookModel: BookModel): Int {
        lastInvoked = "update"

        return 1
    }

    override suspend fun delete(id: Long): Int {
        lastInvoked = "delete"

        return 1
    }
}