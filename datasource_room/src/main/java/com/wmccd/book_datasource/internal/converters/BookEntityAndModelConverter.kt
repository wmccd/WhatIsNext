package com.wmccd.book_datasource.internal.converters

import com.wmccd.book_datasource.internal.books.BookEntity
import com.wmccd.common_models_types.external.models.books.BookModel

internal class BookEntityAndModelConverter {

    fun convert(bookEntity: BookEntity): BookModel {
        return BookModel(
            id = bookEntity.id ?: -1,
            author = bookEntity.author,
            title = bookEntity.title
        )
    }

    fun convert(bookModel: BookModel): BookEntity {
        return BookEntity(
            id = if(bookModel.id == -1L) null else bookModel.id,
            author = bookModel.author,
            title = bookModel.title
        )
    }
}