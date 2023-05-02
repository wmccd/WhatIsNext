package com.wmccd.book_domain.external.usecases

import com.wmccd.common_models_types.external.models.books.BookModel

interface RandomBookUseCase {
    suspend fun execute(): BookModel
}

