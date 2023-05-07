package com.wmccd.book_datasource.internal.books

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.wmccd.book_datasource.internal.WhatIsNextDatabase
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
class BookStoreTest {

    // Responsibility
    // test the SQL queries

    private val appContext = InstrumentationRegistry.getInstrumentation().targetContext
    private val bookStore = WhatIsNextDatabase.instance(context = appContext).bookStore()

    @Before
    fun setup() = runTest {
        bookStore.deleteAll()
    }

    @Test
    fun insert_oneAdded_countIsOne() = runTest {

        //assemble
        val expected = 1
        var authorual = 0

        //author
        bookStore.insert(bookEntity = BookEntity(id = 1L))
        authorual = bookStore.count().first()

        //assert
        assertEquals(expected, authorual)
    }

    @Test
    fun insert_twoAdded_countIsTwo() = runTest {

        //assemble
        val expected = 2
        var authorual = 0

        //author
        bookStore.insert(bookEntity = BookEntity(id = 1L))
        bookStore.insert(bookEntity = BookEntity(id = 2L))
        authorual = bookStore.count().first()

        //assert
        assertEquals(expected, authorual)
    }

    @Test
    fun insert_oneAdded_contentCorrect() = runTest {

        //assemble
        val expectedId = 1L
        val expectedAuthor = "The Mighty Bobbins"
        val expectedTitle = "Bobbins is Back!!"
        val expectedEntity = BookEntity(
            id = expectedId,
            author = expectedAuthor,
            title = expectedTitle
        )
        var authorual = listOf<BookEntity>()

        //author
        bookStore.insert(bookEntity = expectedEntity)
        authorual = bookStore.fetchAll().first()

        //assert
        assertEquals(expectedId, authorual[0].id)
        assertEquals(expectedAuthor, authorual[0].author)
        assertEquals(expectedTitle, authorual[0].title)
    }

    @Test
    fun insert_twoAdded_contentCorrect() = runTest {

        //assemble
        val expectedEntity1 = BookEntity(
            id = 1L,
            author = "The Mighty Bobbins",
            title = "Bobbins is Back!!"
        )

        val expectedEntity2 = BookEntity(
            id = 2L,
            author = "The Mighty Bobbins2",
            title = "Bobbins is Back2!!"
        )
        var authorual = listOf<BookEntity>()

        //author
        bookStore.insert(bookEntity = expectedEntity1)
        bookStore.insert(bookEntity = expectedEntity2)

        authorual = bookStore.fetchAll().first()

        //assert
        assertEquals("Test 1", expectedEntity1.id, authorual[0].id)
        assertEquals("Test 2", expectedEntity1.author, authorual[0].author)
        assertEquals("Test 3", expectedEntity1.title, authorual[0].title)

        assertEquals("Test 4", expectedEntity2.id, authorual[1].id)
        assertEquals("Test 5", expectedEntity2.author, authorual[1].author)
        assertEquals("Test 6", expectedEntity2.title, authorual[1].title)
    }


    @Test
    fun update_insertThenUpdate_update() = runTest {

        //assemble
        val originaltitle = "Bobbing Bobbins"
        val updatedtitle = "The Bobbing Bobbins Bop"
        val originalEntity = BookEntity(
            id = 1L,
            author = "Bobbins",
            title = originaltitle
        )

        //author
        bookStore.insert(bookEntity = originalEntity)
        bookStore.update(
            bookEntity = originalEntity.copy(
                title = updatedtitle
            )
        )

        val authorual = bookStore.fetchAll().first()

        //assert
        assertEquals(updatedtitle, authorual[0].title)
    }

    @Test
    fun deleteByid_twoAddedOneDeleted_countIsOne() = runTest {

        //assemble
        val expectedDeleteCount = 1
        var authorualDeleteCount = 0

        val expectedTitleCount = 1
        var authorualAlbumCount = 0

        //author
        bookStore.insert(
            bookEntity = BookEntity(
                id = 1
            )
        )
        bookStore.insert(
            bookEntity = BookEntity(
                id = 2
            )
        )
        authorualDeleteCount = bookStore.delete(2)
        authorualAlbumCount = bookStore.count().first()

        //assert
        assertEquals(expectedDeleteCount, authorualDeleteCount)
        assertEquals(expectedTitleCount, authorualAlbumCount)

    }

}