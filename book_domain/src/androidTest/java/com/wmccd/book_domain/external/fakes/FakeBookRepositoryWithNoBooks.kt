package com.wmccd.book_domain.external.fakes

import com.wmccd.common_models.external.books.BookModel
import com.wmccd.book_repository.external.BookRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeBookRepositoryWithNoBooks: BookRepository {
    override val books: Flow<List<BookModel>> = flow{
        emit(
            listOf()
        )
    }

    override val count: Flow<Int> = flow{
        emit(0)
    }

    override suspend fun insert(bookModel: BookModel) {
    }

    override suspend fun update(bookModel: BookModel): Int {
        return 0
    }

    override suspend fun delete(id: Long): Int {
        return 0
    }
}