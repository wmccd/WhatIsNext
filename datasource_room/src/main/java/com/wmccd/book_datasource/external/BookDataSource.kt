package com.wmccd.book_datasource.external

import com.wmccd.common_models_types.external.models.books.BookModel
import kotlinx.coroutines.flow.Flow

interface BookDataSource {
    // Responsibility
    // defines the contract of behaviour with a data source (production or mocked)
    // public so that higher level classes can inject mocked implementations

    val books : Flow<List<BookModel>>
    val count : Flow<Int>

    suspend fun insert(bookModel: BookModel)
    suspend fun update(bookModel: BookModel): Int
    suspend fun delete(id: Long): Int

}