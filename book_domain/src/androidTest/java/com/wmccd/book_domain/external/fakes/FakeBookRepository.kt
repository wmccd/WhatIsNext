package com.wmccd.book_domain.external.fakes

import com.wmccd.common_models_types.external.models.books.BookModel
import com.wmccd.book_repository.external.BookRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeBookRepository: BookRepository {
    override val books: Flow<List<BookModel>> = flow{
        emit(
            listOf(
                BookModel(id=1L, author = "A", title = "B"),
                BookModel(id=2L, author = "C", title = "D")
            )
        )
    }

    override val count: Flow<Int> = flow{
        emit(2)
    }

    override suspend fun insert(bookModel: BookModel) {
    }

    override suspend fun update(bookModel: BookModel): Int {
        return 1
    }

    override suspend fun delete(id: Long): Int {
        return 1
    }
}