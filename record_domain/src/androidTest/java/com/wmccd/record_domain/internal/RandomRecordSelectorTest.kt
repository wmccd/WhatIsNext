package com.wmccd.record_domain.internal

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.wmccd.common_models.external.records.RecentRandomRecordModel
import com.wmccd.common_models.external.records.RecordModel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Exception
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class RandomRecordSelectorTest {

    @Test
    fun generateEligibleRandomRecordChoices_emptyRecordList_noChoices(){

        //assemble
        val randomRecordSelector = RandomRecordSelector()
        val recentRecords = listOf<RecentRandomRecordModel>()
        val windowSize = 2
        val records = listOf<RecordModel>()
        val expectedSize = 0

        //act
        val actual = randomRecordSelector.generateEligibleRandomRecordChoices(
            recentRecords = recentRecords,
            windowSize = windowSize,
            records = records
        )

        //assert
        Assert.assertEquals(expectedSize, actual.size)
    }

    @Test
    fun generateEligibleRandomRecordChoices_singleRecordZeroRecent_singleChoices(){

        //assemble
        val randomRecordSelector = RandomRecordSelector()
        val recentRecords = listOf<RecentRandomRecordModel>()
        val windowSize = 2
        val records = listOf<RecordModel>(
            RecordModel(id = 1L, act = "A", title = "B")
        )
        val expectedSize = 1

        //act
        val actual = randomRecordSelector.generateEligibleRandomRecordChoices(
            recentRecords = recentRecords,
            windowSize = windowSize,
            records = records
        )

        //assert
        Assert.assertEquals(expectedSize, actual.size)
    }

    @Test
    fun generateEligibleRandomRecordChoices_singleRecordThatIsRecent_noChoices(){

        //assemble
        val randomRecordSelector = RandomRecordSelector()
        val recentRecords = listOf<RecentRandomRecordModel>(
            RecentRandomRecordModel(id=1L, timestamp = 0L)
        )
        val windowSize = 2
        val records = listOf<RecordModel>(
            RecordModel(id = 1L, act = "A", title = "B")
        )
        val expectedSize = 0

        //act
        val actual = randomRecordSelector.generateEligibleRandomRecordChoices(
            recentRecords = recentRecords,
            windowSize = windowSize,
            records = records
        )

        //assert
        Assert.assertEquals(expectedSize, actual.size)
    }

    @Test
    fun generateEligibleRandomRecordChoices_twoRecordsOneThatIsRecent_singleChoice(){

        //assemble
        val randomRecordSelector = RandomRecordSelector()
        val recentRecords = listOf<RecentRandomRecordModel>(
            RecentRandomRecordModel(id=1L, timestamp = 0L)
        )
        val windowSize = 2
        val records = listOf<RecordModel>(
            RecordModel(id = 1L, act = "A", title = "B"),
            RecordModel(id = 2L, act = "C", title = "D")

        )
        val expectedSize = 1

        //act
        val actual = randomRecordSelector.generateEligibleRandomRecordChoices(
            recentRecords = recentRecords,
            windowSize = windowSize,
            records = records
        )

        //assert
        Assert.assertEquals(expectedSize, actual.size)
    }

    @Test
    fun generateEligibleRandomRecordChoices_twoRecordsThatAreRecent_noChoice(){

        //assemble
        val randomRecordSelector = RandomRecordSelector()
        val recentRecords = listOf<RecentRandomRecordModel>(
            RecentRandomRecordModel(id=1L, timestamp = 0L),
            RecentRandomRecordModel(id=2L, timestamp = 0L),
        )
        val windowSize = 2
        val records = listOf<RecordModel>(
            RecordModel(id = 1L, act = "A", title = "B"),
            RecordModel(id = 2L, act = "C", title = "D")

        )
        val expectedSize = 0

        //act
        val actual = randomRecordSelector.generateEligibleRandomRecordChoices(
            recentRecords = recentRecords,
            windowSize = windowSize,
            records = records
        )

        //assert
        Assert.assertEquals(expectedSize, actual.size)
    }

    @Test
    fun generateEligibleRandomRecordChoices_allRecordsAreRecentButMoreThanWindowSize_choicesAvailable(){

        //assemble
        val randomRecordSelector = RandomRecordSelector()
        val recentRecords = listOf<RecentRandomRecordModel>(
            RecentRandomRecordModel(id=1L, timestamp = 0L),
            RecentRandomRecordModel(id=2L, timestamp = 0L),
            RecentRandomRecordModel(id=3L, timestamp = 0L),
            RecentRandomRecordModel(id=4L, timestamp = 0L),
            )
        val windowSize = 2
        val records = listOf<RecordModel>(
            RecordModel(id = 1L, act = "A", title = "B"),
            RecordModel(id = 2L, act = "C", title = "D"),
            RecordModel(id = 3L, act = "E", title = "F"),
            RecordModel(id = 4L, act = "G", title = "H")
        )
        val expectedSize = 2

        //act
        val actual = randomRecordSelector.generateEligibleRandomRecordChoices(
            recentRecords = recentRecords,
            windowSize = windowSize,
            records = records
        )

        //assert
        Assert.assertEquals(expectedSize, actual.size)
    }

    @Test
    fun generateEligibleRandomRecordChoices_allRecordsAreRecentButMoreThanWindowSize_mostRecentRemoved() = runTest{

        //assemble
        val randomRecordSelector = RandomRecordSelector()
        val recentRecords = listOf<RecentRandomRecordModel>(
            RecentRandomRecordModel(id=1L, timestamp = 104L),
            RecentRandomRecordModel(id=2L, timestamp = 101L),
            RecentRandomRecordModel(id=3L, timestamp = 103L),
            RecentRandomRecordModel(id=4L, timestamp = 102L),
        )
        val windowSize = 2
        val records = listOf<RecordModel>(
            RecordModel(id = 1L, act = "A", title = "B"),
            RecordModel(id = 2L, act = "C", title = "D"),
            RecordModel(id = 3L, act = "E", title = "F"),
            RecordModel(id = 4L, act = "G", title = "H")
        )

        //act
        val actual = randomRecordSelector.generateEligibleRandomRecordChoices(
            recentRecords = recentRecords,
            windowSize = windowSize,
            records = records
        )
        advanceUntilIdle()

        //assert
        Assert.assertEquals( "1: $actual",1, actual.filter { it.id == 2L  }.size )
        Assert.assertEquals( "2: $actual",1, actual.filter { it.id == 4L  }.size )
    }

    @Test
    fun chooseAlbum_emptyList_exception() = runTest{

        //assemble
        val randomRecordSelector = RandomRecordSelector()
        val recentRecords = arrayListOf<RecentRandomRecordModel>()
        val records = arrayListOf<RecordModel>()
        val countDownLatch = CountDownLatch(1)
        val expected = 0L

        //act
        try{
            randomRecordSelector.select(
                recentRecords= recentRecords,
                windowSize= 2,
                records= records,
                report = {}
            )
        }catch(ex: Exception){
            countDownLatch.countDown()
        }

        countDownLatch.await(100, TimeUnit.MILLISECONDS)

        //assert
        Assert.assertEquals(expected, countDownLatch.count)
    }

    @Test
    fun chooseAlbum_oneRecord_selected() = runTest{

        //assemble
        val randomRecordSelector = RandomRecordSelector()
        val recentRecords = arrayListOf<RecentRandomRecordModel>()
        val expectedRecord = RecordModel(id = 1L, act = "A", title = "B")
        val records = arrayListOf<RecordModel>(
            expectedRecord
        )

        //act
        val actual = randomRecordSelector.select(
            recentRecords= recentRecords,
            windowSize= 2,
            records= records,
            report = {}
        )

        //assert
        Assert.assertEquals(expectedRecord.id, actual.id)
    }
}