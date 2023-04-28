package com.wmccd.book_domain.external

import com.wmccd.book_domain.external.usecases.AllBooksUseCase
import com.wmccd.book_domain.external.usecases.DeleteBookUseCase
import com.wmccd.book_domain.external.usecases.InsertBookUseCase
import com.wmccd.book_domain.external.usecases.RandomBookUseCase
import com.wmccd.book_domain.external.usecases.UpdateBookUseCase

data class BookUseCases (
    val allBooksUseCase: AllBooksUseCase,
    val deleteBookUseCase: DeleteBookUseCase,
    val insertBookUseCase: InsertBookUseCase,
    val randomBookUseCase: RandomBookUseCase,
    val updateBookUseCase: UpdateBookUseCase
)