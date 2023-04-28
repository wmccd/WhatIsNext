package com.wmccd.book_domain.external.usecases

import com.wmccd.common_models.external.books.BookModel

interface InsertBookUseCase {
    suspend fun execute(bookModel: BookModel)
}

