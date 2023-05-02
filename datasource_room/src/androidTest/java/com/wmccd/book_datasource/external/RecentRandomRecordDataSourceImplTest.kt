package com.wmccd.book_datasource.external

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.wmccd.book_datasource.external.fakes.FakeAnalogueReporter
import com.wmccd.book_datasource.internal.WhatIsNextDatabase
import com.wmccd.common_models_types.external.models.records.RecentRandomRecordModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class RecentRandomRecordDataSourceImplTest {

    // Responsibility
    // test the SQL queries

    private val appContext = InstrumentationRegistry.getInstrumentation().targetContext
    private val recentRandomRecordDataStore = WhatIsNextDatabase.instance(appContext).recentRandomRecordStore()
    private val recentRandomRecordDataSource = RecentRandomRecordDataSourceImpl(
        context = appContext,
        analogueReporter = FakeAnalogueReporter()
    )

    @Before
    fun setup() = runTest {
        recentRandomRecordDataStore.deleteAll()
    }

    @Test
    fun insert_oneAdded_countIsOne() = runTest {

        //assemble
        val expected = 1
        var actual = 0

        //author
        recentRandomRecordDataSource.insert(recentRandomRecordModel = RecentRandomRecordModel())
        actual = recentRandomRecordDataSource.count.first()

        //assert
        assertEquals(expected, actual)
    }

    @Test
    fun insert_twoAdded_countIsTwo() = runTest {

        //assemble
        val expected = 2
        var actual = 0

        //author
        recentRandomRecordDataSource.insert(recentRandomRecordModel = RecentRandomRecordModel(
            timestamp = 1L
        )
        )
        recentRandomRecordDataSource.insert(recentRandomRecordModel = RecentRandomRecordModel(
            timestamp = 2L
        )
        )
        actual = recentRandomRecordDataSource.count.first()

        //assert
        assertEquals(expected, actual)
    }

    @Test
    fun insert_oneAdded_contentCorrect() = runTest {

        //assemble
        val expectedId = 1L
        val expectedTimestamp = 2L
        val expectedModel = RecentRandomRecordModel(
            id = expectedId,
            timestamp = expectedTimestamp,
        )

        //author
        recentRandomRecordDataSource.insert(recentRandomRecordModel = expectedModel)
        val actual = recentRandomRecordDataSource.recentRandomRecords.first()

        //assert
        assertEquals("1 <$actual>", expectedId, actual[0].id)
        assertEquals("2 <$actual>", expectedTimestamp, actual[0].timestamp)
    }

    @Test
    fun insert_twoAdded_contentCorrect() = runTest {

        //assemble
        val expectedModel1 = RecentRandomRecordModel(
            id = 1L,
            timestamp = 3L
        )

        val expectedModel2 = RecentRandomRecordModel(
            id = 2L,
            timestamp = 4L
        )

        //author
        recentRandomRecordDataSource.insert(recentRandomRecordModel = expectedModel1)
        recentRandomRecordDataSource.insert(recentRandomRecordModel = expectedModel2)

        val actual = recentRandomRecordDataSource.recentRandomRecords.first()

        //assert
        assertEquals("Test 1", expectedModel1.id, actual[1].id)
        assertEquals("Test 2", expectedModel1.timestamp, actual[1].timestamp)

        assertEquals("Test 4", expectedModel2.id, actual[0].id)
        assertEquals("Test 5", expectedModel2.timestamp, actual[0].timestamp)
    }



}