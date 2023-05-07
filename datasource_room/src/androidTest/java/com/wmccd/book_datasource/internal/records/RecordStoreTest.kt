package com.wmccd.book_datasource.internal.records

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
class RecordStoreTest {

    // Responsibility
    // test the SQL queries

    private val appContext = InstrumentationRegistry.getInstrumentation().targetContext
    private val recordStore = WhatIsNextDatabase.instance(context = appContext).recordStore()

    @Before
    fun setup() = runTest {
        recordStore.deleteAll()
    }

    @Test
    fun insert_oneAdded_countIsOne() = runTest {

        //assemble
        val expected = 1
        var actual = 0

        //act
        recordStore.insert(recordEntity = RecordEntity(id = 1L))
        actual = recordStore.count().first()

        //assert
        assertEquals(expected, actual)
    }

    @Test
    fun insert_twoAdded_countIsTwo() = runTest {

        //assemble
        val expected = 2
        var actual = 0

        //act
        recordStore.insert(recordEntity = RecordEntity(id = 1L))
        recordStore.insert(recordEntity = RecordEntity(id = 2L))
        actual = recordStore.count().first()

        //assert
        assertEquals(expected, actual)
    }

    @Test
    fun insert_oneAdded_contentCorrect() = runTest {

        //assemble
        val expectedId = 1L
        val expectedAct = "The Mighty Bobbins"
        val expectedTitle = "Bobbins is Back!!"
        val expectedEntity = RecordEntity(
            id = expectedId,
            act = expectedAct,
            title = expectedTitle
        )
        var actual = listOf<RecordEntity>()

        //act
        recordStore.insert(recordEntity = expectedEntity)
        actual = recordStore.fetchAll().first()

        //assert
        assertEquals(expectedId, actual[0].id)
        assertEquals(expectedAct, actual[0].act)
        assertEquals(expectedTitle, actual[0].title)
    }

    @Test
    fun insert_twoAdded_contentCorrect() = runTest {

        //assemble
        val expectedEntity1 = RecordEntity(
            id = 1L,
            act = "The Mighty Bobbins",
            title = "Bobbins is Back!!"
        )

        val expectedEntity2 = RecordEntity(
            id = 2L,
            act = "The Mighty Bobbins2",
            title = "Bobbins is Back2!!"
        )
        var actual = listOf<RecordEntity>()

        //act
        recordStore.insert(recordEntity = expectedEntity1)
        recordStore.insert(recordEntity = expectedEntity2)

        actual = recordStore.fetchAll().first()

        //assert
        assertEquals("Test 1", expectedEntity1.id, actual[0].id)
        assertEquals("Test 2", expectedEntity1.act, actual[0].act)
        assertEquals("Test 3", expectedEntity1.title, actual[0].title)

        assertEquals("Test 4", expectedEntity2.id, actual[1].id)
        assertEquals("Test 5", expectedEntity2.act, actual[1].act)
        assertEquals("Test 6", expectedEntity2.title, actual[1].title)
    }


    @Test
    fun update_insertThenUpdate_update() = runTest {

        //assemble
        val originaltitle = "Bobbing Bobbins"
        val updatedtitle = "The Bobbing Bobbins Bop"
        val originalEntity = RecordEntity(
            id = 1L,
            act = "Bobbins",
            title = originaltitle
        )

        //act
        recordStore.insert(recordEntity = originalEntity)
        recordStore.update(
            recordEntity = originalEntity.copy(
                title = updatedtitle
            )
        )

        val actual = recordStore.fetchAll().first()

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

        //act
        recordStore.insert(
            recordEntity = RecordEntity(
                id = 1
            )
        )
        recordStore.insert(
            recordEntity = RecordEntity(
                id = 2
            )
        )
        actualDeleteCount = recordStore.delete(2)
        actualAlbumCount = recordStore.count().first()

        //assert
        assertEquals(expectedDeleteCount, actualDeleteCount)
        assertEquals(expectedTitleCount, actualAlbumCount)

    }

}