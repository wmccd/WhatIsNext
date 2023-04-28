package com.wmccd.record_repository.external

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.wmccd.common_models.external.records.RecentRandomRecordModel
import com.wmccd.record_repository.external.fakes.FakeAnalogueReporter
import com.wmccd.record_repository.external.fakes.FakeRecentRandomRecordsDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class RecentRandomRecordRepositoryImplTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun records_fetchingFromDataSource_correctCount() = runTest{

        //assemble
        val dataSource = FakeRecentRandomRecordsDataSource()
        val analogueReporter = FakeAnalogueReporter()
        val dispatcher = StandardTestDispatcher(testScheduler)
        val repository = RecentRandomRecordRepositoryImpl(
            dataSource = dataSource,
            analogueReporter = analogueReporter,
            dispatcher = dispatcher
        )
        val expectedCount = 2
        val expectedDataSourceNotice = "fetch all"
        val expectedAnalogueNotice = "Repository: recent random records flow update"


        //act
        val actual = repository.recentRandomRecords.first()

        //assert
        Assert.assertEquals(expectedCount,actual.count() )
        Assert.assertEquals(expectedDataSourceNotice, dataSource.lastInvoked )
        Assert.assertEquals(expectedAnalogueNotice, analogueReporter.lastInvoked )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun count_fetchesFromDataStore_correctValue() = runTest{

        //assemble
        val dataSource = FakeRecentRandomRecordsDataSource()
        val analogueReporter = FakeAnalogueReporter()
        val dispatcher = StandardTestDispatcher(testScheduler)
        val repository = RecentRandomRecordRepositoryImpl(
            dataSource = dataSource,
            analogueReporter = analogueReporter,
            dispatcher = dispatcher
        )
        val expectedCount = 1
        val expectedDataSourceNotice = "count"
        val expectedAnalogueNotice = "Repository: recent random records count flow update"


        //act
        val actual = repository.count.first()

        //assert
        Assert.assertEquals(expectedCount,actual )
        Assert.assertEquals(expectedDataSourceNotice, dataSource.lastInvoked )
        Assert.assertEquals(expectedAnalogueNotice, analogueReporter.lastInvoked )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun insert_invokesDataStore_updateFlag() = runTest{

        //assemble
        val dataSource = FakeRecentRandomRecordsDataSource()
        val analogueReporter = FakeAnalogueReporter()
        val dispatcher = StandardTestDispatcher(testScheduler)
        val repository = RecentRandomRecordRepositoryImpl(
            dataSource = dataSource,
            analogueReporter = analogueReporter,
            dispatcher = dispatcher
        )
        val bookModel = RecentRandomRecordModel()
        val expectedDataSourceNotice = "insert"
        val expectedAnalogueNotice = "Repository: recent random records insert"

        //act
        repository.insert(bookModel)

        //assert
        Assert.assertEquals(expectedDataSourceNotice, dataSource.lastInvoked )
        Assert.assertEquals(expectedAnalogueNotice, analogueReporter.lastInvoked )
    }
}