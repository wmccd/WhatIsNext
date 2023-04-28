package com.wmccd.book_repository.external

import com.wmccd.common_models.external.books.BookModel
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    val books: Flow<List<BookModel>>
    val count : Flow<Int>

    suspend fun insert(bookModel: BookModel)
    suspend fun update(bookModel: BookModel): Int
    suspend fun delete(id: Long): Int
}