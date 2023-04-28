package com.wmccd.book_domain.internal

import com.wmccd.common_exceptions.external.model_validation.InvalidBookAuthorException
import com.wmccd.common_exceptions.external.model_validation.InvalidBookTitleException
import com.wmccd.common_models.external.books.BookModel

internal class BookChecker {

    fun check(bookModel: BookModel){
        if(bookModel.author.isBlank())
            throw InvalidBookAuthorException(message = bookModel.toString())

        if(bookModel.title.isBlank())
            throw InvalidBookTitleException(message = bookModel.toString())
    }
}