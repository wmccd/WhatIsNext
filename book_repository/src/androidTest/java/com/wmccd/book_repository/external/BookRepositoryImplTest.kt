package com.wmccd.book_repository.external

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.wmccd.common_models_types.external.models.books.BookModel
import com.wmccd.book_repository.external.fakes.FakeAnalogueReporter
import com.wmccd.book_repository.external.fakes.FakeBookDataSource
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
class BookRepositoryImplTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun books_fetchingFromDataSource_correctCount() = runTest{

        //assemble
        val dataSource = FakeBookDataSource()
        val analogueReporter = FakeAnalogueReporter()
        val dispatcher = StandardTestDispatcher(testScheduler)
        val repository = BookRepositoryImpl(
            dataSource = dataSource,
            analogueReporter = analogueReporter,
            dispatcher = dispatcher
        )
        val expectedCount = 2
        val expectedDataSourceNotice = "fetch all"
        val expectedAnalogueNotice = "Repository: books flow update"

        //act
        val actual = repository.books.first()

        //assert
        Assert.assertEquals(expectedCount, actual.count())
        Assert.assertEquals(expectedDataSourceNotice, dataSource.lastInvoked )
        Assert.assertEquals(expectedAnalogueNotice, analogueReporter.lastInvoked )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun count_fetchesFromDataStore_correctValue() = runTest{

        //assemble
        val dataSource = FakeBookDataSource()
        val analogueReporter = FakeAnalogueReporter()
        val dispatcher = StandardTestDispatcher(testScheduler)
        val repository = BookRepositoryImpl(
            dataSource = dataSource,
            analogueReporter = analogueReporter,
            dispatcher = dispatcher
        )
        val expectedCount = 77
        val expectedDataSourceNotice = "count"
        val expectedAnalogueNotice = "Repository: books count flow update"

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
        val dataSource = FakeBookDataSource()
        val analogueReporter = FakeAnalogueReporter()
        val dispatcher = StandardTestDispatcher(testScheduler)
        val repository = BookRepositoryImpl(
            dataSource = dataSource,
            analogueReporter = analogueReporter,
            dispatcher = dispatcher
        )
        val bookModel = BookModel()
        val expectedDataSourceNotice = "insert"
        val expectedAnalogueNotice = "Repository: book insert"

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
        val dataSource = FakeBookDataSource()
        val analogueReporter = FakeAnalogueReporter()
        val dispatcher = StandardTestDispatcher(testScheduler)
        val repository = BookRepositoryImpl(
            dataSource = dataSource,
            analogueReporter = analogueReporter,
            dispatcher = dispatcher
        )
        val bookModel = BookModel()
        val expectedDataSourceNotice = "update"
        val expectedAnalogueNotice = "Repository: book update"

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
        val dataSource = FakeBookDataSource()
        val analogueReporter = FakeAnalogueReporter()
        val dispatcher = StandardTestDispatcher(testScheduler)
        val repository = BookRepositoryImpl(
            dataSource = dataSource,
            analogueReporter = analogueReporter,
            dispatcher = dispatcher
        )
        val expectedCount = 1
        val expectedDataSourceNotice = "delete"
        val expectedAnalogueNotice = "Repository: book delete"

        //act
        val actual = repository.delete(1L)
        CountDownLatch(1).await(100, TimeUnit.MILLISECONDS)

        //assert
        Assert.assertEquals(expectedCount,actual )
        Assert.assertEquals(expectedDataSourceNotice, dataSource.lastInvoked )
        Assert.assertEquals(expectedAnalogueNotice, analogueReporter.lastInvoked )
    }
}