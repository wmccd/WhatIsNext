package com.wmccd.record_repository.external

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.wmccd.common_models.external.records.RecordModel
import com.wmccd.record_repository.external.fakes.FakeAnalogueReporter
import com.wmccd.record_repository.external.fakes.FakeRecordDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class RecordRepositoryImplTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun books_fetchingFromDataSource_correctCount() = runTest{

        //assemble
        val dataSource = FakeRecordDataSource()
        val analogueReporter = FakeAnalogueReporter()
        val dispatcher = StandardTestDispatcher(testScheduler)
        val repository = RecordRepositoryImpl(
            dataSource = dataSource,
            analogueReporter = analogueReporter,
            dispatcher = dispatcher
        )
        val expectedCount = 2
        val expectedDataSourceNotice = "fetch all"
        val expectedAnalogueNotice = "Repository: record flow update"


        //act
        val actual = repository.records.first()

        //assert
        Assert.assertEquals(expectedCount,actual.count() )
        Assert.assertEquals(expectedDataSourceNotice, dataSource.lastInvoked )
        Assert.assertEquals(expectedAnalogueNotice, analogueReporter.lastInvoked )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun count_fetchesFromDataStore_correctValue() = runTest{

        //assemble
        val dataSource = FakeRecordDataSource()
        val analogueReporter = FakeAnalogueReporter()
        val dispatcher = StandardTestDispatcher(testScheduler)
        val repository = RecordRepositoryImpl(
            dataSource = dataSource,
            analogueReporter = analogueReporter,
            dispatcher = dispatcher
        )
        val expectedCount = 1
        val expectedDataSourceNotice = "count"
        val expectedAnalogueNotice = "Repository: record count flow update"


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
        val dataSource = FakeRecordDataSource()
        val analogueReporter = FakeAnalogueReporter()
        val dispatcher = StandardTestDispatcher(testScheduler)
        val repository = RecordRepositoryImpl(
            dataSource = dataSource,
            analogueReporter = analogueReporter,
            dispatcher = dispatcher
        )
        val bookModel = RecordModel()
        val expectedDataSourceNotice = "insert"
        val expectedAnalogueNotice = "Repository: record insert"


        //act
        repository.insert(bookModel)

        //assert
        Assert.assertEquals("insert",dataSource.lastInvoked )
        Assert.assertEquals(expectedDataSourceNotice, dataSource.lastInvoked )
        Assert.assertEquals(expectedAnalogueNotice, analogueReporter.lastInvoked )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun update_invokesDataStore_updateFlag() = runTest{

        //assemble
        val dataSource = FakeRecordDataSource()
        val analogueReporter = FakeAnalogueReporter()
        val dispatcher = StandardTestDispatcher(testScheduler)
        val repository = RecordRepositoryImpl(
            dataSource = dataSource,
            analogueReporter = analogueReporter,
            dispatcher = dispatcher
        )
        val bookModel = RecordModel()
        val expectedDataSourceNotice = "update"
        val expectedAnalogueNotice = "Repository: record update"


        //act
        repository.update(bookModel)

        //assert
        Assert.assertEquals("update",dataSource.lastInvoked )
        Assert.assertEquals(expectedDataSourceNotice, dataSource.lastInvoked )
        Assert.assertEquals(expectedAnalogueNotice, analogueReporter.lastInvoked )

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun delete_invokesDataStore_updateFlag() = runTest{

        //assemble
        val dataSource = FakeRecordDataSource()
        val analogueReporter = FakeAnalogueReporter()
        val dispatcher = StandardTestDispatcher(testScheduler)
        val repository = RecordRepositoryImpl(
            dataSource = dataSource,
            analogueReporter = analogueReporter,
            dispatcher = dispatcher
        )
        val expectedCount = 1
        val expectedDataSourceNotice = "delete"
        val expectedAnalogueNotice = "Repository: record delete"

        //act
        val actual = repository.delete(1L)
        CountDownLatch(1).await(100, TimeUnit.MILLISECONDS)

        //assert
        Assert.assertEquals(expectedCount,actual )
        Assert.assertEquals(expectedDataSourceNotice, dataSource.lastInvoked )
        Assert.assertEquals(expectedAnalogueNotice, analogueReporter.lastInvoked )
    }
}