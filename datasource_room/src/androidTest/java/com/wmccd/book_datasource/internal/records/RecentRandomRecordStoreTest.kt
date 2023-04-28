package com.wmccd.book_datasource.internal.records

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.wmccd.book_datasource.internal.WhatIsNextDatabase
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
class RecentRandomRecordStoreTest {

    // Responsibility
    // test the SQL queries

    private val appContext = InstrumentationRegistry.getInstrumentation().targetContext
    private val recentRandomRecordStore = WhatIsNextDatabase.instance(context = appContext).recentRandomRecordStore()

    @Before
    fun setup() = runTest {
        recentRandomRecordStore.deleteAll()
    }

    @Test
    fun insert_oneAdded_countIsOne() = runTest {

        //assemble
        val expected = 1
        var actual = 0

        //author
        recentRandomRecordStore.insert(recentRandomRecordEntity = RecentRandomRecordEntity())
        actual = recentRandomRecordStore.count().first()

        //assert
        assertEquals(expected, actual)
    }

    @Test
    fun insert_twoAdded_countIsTwo() = runTest {

        //assemble
        val expected = 2
        var actual = 0


        //author
        recentRandomRecordStore.insert(recentRandomRecordEntity = RecentRandomRecordEntity(
            timestamp = 1L
        ))
        recentRandomRecordStore.insert(recentRandomRecordEntity = RecentRandomRecordEntity(
            timestamp = 2L
        ))
        actual = recentRandomRecordStore.count().first()

        //assert
        assertEquals(expected, actual)
    }

    @Test
    fun insert_oneAdded_contentCorrect() = runTest {

        //assemble
        val expectedId = 1L
        val expectedTimestamp = 2L
        val expectedEntity = RecentRandomRecordEntity(
            id = expectedId,
            timestamp = expectedTimestamp
        )
        var actual = listOf<RecentRandomRecordEntity>()

        //author
        recentRandomRecordStore.insert(recentRandomRecordEntity = expectedEntity)
        actual = recentRandomRecordStore.fetchAll().first()

        //assert
        assertEquals(expectedId, actual[0].id)
        assertEquals(expectedTimestamp, actual[0].timestamp)
    }

    @Test
    fun insert_twoAdded_contentCorrectAndDesc() = runTest {

        //assemble
        val expectedEntity1 = RecentRandomRecordEntity(
            id = 1L,
            timestamp = 2L,
        )

        val expectedEntity2 = RecentRandomRecordEntity(
            id = 2L,
            timestamp = 3L,
        )
        var actual = listOf<RecentRandomRecordEntity>()

        //author
        recentRandomRecordStore.insert(recentRandomRecordEntity = expectedEntity1)
        recentRandomRecordStore.insert(recentRandomRecordEntity = expectedEntity2)

        actual = recentRandomRecordStore.fetchAll().first()

        //assert
        assertEquals("Test 1", expectedEntity1.id, actual[1].id)
        assertEquals("Test 2", expectedEntity1.timestamp, actual[1].timestamp)

        assertEquals("Test 4", expectedEntity2.id, actual[0].id)
        assertEquals("Test 5", expectedEntity2.timestamp, actual[0].timestamp)
    }


}