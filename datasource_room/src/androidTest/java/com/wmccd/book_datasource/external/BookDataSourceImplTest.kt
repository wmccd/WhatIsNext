package com.wmccd.book_datasource.external

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.wmccd.book_datasource.external.fakes.FakeAnalogueReporter
import com.wmccd.book_datasource.internal.WhatIsNextDatabase
import com.wmccd.common_models_types.external.models.books.BookModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class BookDataSourceImplTest {

    // Responsibility
    // test the SQL queries

    private val appContext = InstrumentationRegistry.getInstrumentation().targetContext
    private val bookStore = WhatIsNextDatabase.instance(appContext).bookStore()
    private val bookDataSource = BookDataSourceImpl(
        context = appContext,
        analogueReporter = FakeAnalogueReporter()
    )

    @Before
    fun setup() = runTest {
        bookStore.deleteAll()
    }

    @Test
    fun insert_oneAdded_countIsOne() = runTest {

        //assemble
        val expected = 1
        var actual = 0

        //author
        bookDataSource.insert(bookModel = BookModel())
        actual = bookDataSource.count.first()

        //assert
        assertEquals(expected, actual)
    }

    @Test
    fun insert_twoAdded_countIsTwo() = runTest {

        //assemble
        val expected = 2
        var actual = 0

        //author
        bookDataSource.insert(bookModel = BookModel())
        bookDataSource.insert(bookModel = BookModel())
        actual = bookDataSource.count.first()

        //assert
        assertEquals(expected, actual)
    }

    @Test
    fun insert_oneAdded_contentCorrect() = runTest {

        //assemble
        val expectedId = 1L
        val expectedAuthor = "The Mighty Bobbins"
        val expectedTitle = "Bobbins is Back!!"
        val expectedModel = BookModel(
            id = expectedId,
            author = expectedAuthor,
            title = expectedTitle
        )

        //author
        bookDataSource.insert(bookModel = expectedModel)
        val actual = bookDataSource.books.first()

        //assert
        assertEquals("1 <$actual>", expectedId, actual[0].id)
        assertEquals("2 <$actual>", expectedAuthor, actual[0].author)
        assertEquals("3 <$actual>", expectedTitle, actual[0].title)
    }

    @Test
    fun insert_twoAdded_contentCorrect() = runTest {

        //assemble
        val expectedModel1 = BookModel(
            id = 1L,
            author = "The Mighty Bobbins",
            title = "Bobbins is Back!!"
        )

        val expectedModel2 = BookModel(
            id = 2L,
            author = "The Mighty Bobbins2",
            title = "Bobbins is Back2!!"
        )

        //author
        bookDataSource.insert(bookModel = expectedModel1)
        bookDataSource.insert(bookModel = expectedModel2)

        val actual = bookDataSource.books.first()

        //assert
        assertEquals("Test 1", expectedModel1.id, actual[0].id)
        assertEquals("Test 2", expectedModel1.author, actual[0].author)
        assertEquals("Test 3", expectedModel1.title, actual[0].title)

        assertEquals("Test 4", expectedModel2.id, actual[1].id)
        assertEquals("Test 5", expectedModel2.author, actual[1].author)
        assertEquals("Test 6", expectedModel2.title, actual[1].title)
    }


    @Test
    fun update_insertThenUpdate_update() = runTest {

        //assemble
        val originaltitle = "Bobbing Bobbins"
        val updatedtitle = "The Bobbing Bobbins Bop"
        val originalModel = BookModel(
            id = 1L,
            author = "Bobbins",
            title = originaltitle
        )

        //author
        bookDataSource.insert(bookModel = originalModel)
        bookDataSource.update(
            bookModel = originalModel.copy(
                title = updatedtitle
            )
        )

        val actual = bookDataSource.books.first()

        //assert
        assertEquals(updatedtitle, actual[0].title)
    }

    @Test
    fun deleteByid_twoAddedOneDeleted_countIsOne() = runTest {

        //assemble
        val expectedDeleteCount = 1
        var actualDeleteCount = 0

        val expectedTitleCount = 1
        var actualAlbumCount = 0

        //author
        bookDataSource.insert(
            bookModel = BookModel(
                id = 1
            )
        )
        bookDataSource.insert(
            bookModel = BookModel(
                id = 2
            )
        )
        actualDeleteCount = bookDataSource.delete(2)
        actualAlbumCount = bookDataSource.count.first()

        //assert
        assertEquals(expectedDeleteCount, actualDeleteCount)
        assertEquals(expectedTitleCount, actualAlbumCount)

    }

}