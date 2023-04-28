package com.wmccd.book_datasource.internal.converters

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.wmccd.book_datasource.internal.books.BookEntity
import com.wmccd.common_models.external.books.BookModel
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BookEntityAndModelConverterTest {

    @Test
    fun convert_modelToEntity_correctValues(){

        //assemble
        val converter = BookEntityAndModelConverter()
        val expectedId = 1L
        val expectedAuthor = "A"
        val expectedTitle = "B"
        val model = BookModel(
            id = expectedId,
            author = expectedAuthor,
            title = expectedTitle
        )

        //act
        val actual = converter.convert( bookModel = model)

        //assert
        Assert.assertEquals(expectedId, actual.id)
        Assert.assertEquals(expectedAuthor, actual.author)
        Assert.assertEquals(expectedTitle, actual.title)
    }

    @Test
    fun convert_entityToModel_correctValues(){

        //assemble
        val converter = BookEntityAndModelConverter()
        val expectedId = 1L
        val expectedAuthor = "A"
        val expectedTitle = "B"
        val entity = BookEntity(
            id = expectedId,
            author = expectedAuthor,
            title = expectedTitle
        )

        //act
        val actual = converter.convert( bookEntity = entity)

        //assert
        Assert.assertEquals(expectedId, actual.id)
        Assert.assertEquals(expectedAuthor, actual.author)
        Assert.assertEquals(expectedTitle, actual.title)
    }


}