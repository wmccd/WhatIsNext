package com.wmccd.book_domain.external.usecases

import com.wmccd.common_models.external.books.BookModel
import kotlinx.coroutines.flow.Flow

interface AllBooksUseCase {
    suspend fun execute(): Flow<List<BookModel>>
}

