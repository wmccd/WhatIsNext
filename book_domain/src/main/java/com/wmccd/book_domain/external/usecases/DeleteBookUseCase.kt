package com.wmccd.book_domain.external.usecases

interface DeleteBookUseCase {
    suspend fun execute(id:Long)
}

