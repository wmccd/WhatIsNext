package com.wmccd.book_datasource.external

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.wmccd.book_datasource.external.fakes.FakeAnalogueReporter
import com.wmccd.book_datasource.internal.WhatIsNextDatabase
import com.wmccd.common_models_types.external.models.records.RecordModel
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
class RecordDataSourceImplTest {

    // Responsibility
    // test the SQL queries

    private val appContext = InstrumentationRegistry.getInstrumentation().targetContext
    private val recordStore = WhatIsNextDatabase.instance(appContext).recordStore()
    private val recordDataSource = RecordDataSourceImpl(
        context = appContext,
        analogueReporter = FakeAnalogueReporter()
    )

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
        recordDataSource.insert(recordModel = RecordModel())
        actual = recordDataSource.count.first()

        //assert
        assertEquals(expected, actual)
    }

    @Test
    fun insert_twoAdded_countIsTwo() = runTest {

        //assemble
        val expected = 2
        var actual = 0

        //act
        recordDataSource.insert(recordModel = RecordModel())
        recordDataSource.insert(recordModel = RecordModel())
        actual = recordDataSource.count.first()

        //assert
        assertEquals(expected, actual)
    }

    @Test
    fun insert_oneAdded_contentCorrect() = runTest {

        //assemble
        val expectedId = 1L
        val expectedAct = "The Mighty Bobbins"
        val expectedTitle = "Bobbins is Back!!"
        val expectedModel = RecordModel(
            id = expectedId,
            act = expectedAct,
            title = expectedTitle
        )

        //act
        recordDataSource.insert(recordModel = expectedModel)
        val actual = recordDataSource.records.first()

        //assert
        assertEquals("1 <$actual>", expectedId, actual[0].id)
        assertEquals("2 <$actual>", expectedAct, actual[0].act)
        assertEquals("3 <$actual>", expectedTitle, actual[0].title)
    }

    @Test
    fun insert_twoAdded_contentCorrect() = runTest {

        //assemble
        val expectedModel1 = RecordModel(
            id = 1L,
            act = "The Mighty Bobbins",
            title = "Bobbins is Back!!"
        )

        val expectedModel2 = RecordModel(
            id = 2L,
            act = "The Mighty Bobbins2",
            title = "Bobbins is Back2!!"
        )

        //act
        recordDataSource.insert(recordModel = expectedModel1)
        recordDataSource.insert(recordModel = expectedModel2)

        val actual = recordDataSource.records.first()

        //assert
        assertEquals("Test 1", expectedModel1.id, actual[0].id)
        assertEquals("Test 2", expectedModel1.act, actual[0].act)
        assertEquals("Test 3", expectedModel1.title, actual[0].title)

        assertEquals("Test 4", expectedModel2.id, actual[1].id)
        assertEquals("Test 5", expectedModel2.act, actual[1].act)
        assertEquals("Test 6", expectedModel2.title, actual[1].title)
    }


    @Test
    fun update_insertThenUpdate_update() = runTest {

        //assemble
        val originaltitle = "Bobbing Bobbins"
        val updatedtitle = "The Bobbing Bobbins Bop"
        val originalModel = RecordModel(
            id = 1L,
            act = "Bobbins",
            title = originaltitle
        )

        //act
        recordDataSource.insert(recordModel = originalModel)
        recordDataSource.update(
            recordModel = originalModel.copy(
                title = updatedtitle
            )
        )

        val actual = recordDataSource.records.first()

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
        recordDataSource.insert(
            recordModel = RecordModel(
                id = 1
            )
        )
        recordDataSource.insert(
            recordModel = RecordModel(
                id = 2
            )
        )
        actualDeleteCount = recordDataSource.delete(2)
        actualAlbumCount = recordDataSource.count.first()

        //assert
        assertEquals(expectedDeleteCount, actualDeleteCount)
        assertEquals(expectedTitleCount, actualAlbumCount)
    }

}