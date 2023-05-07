package com.wmccd.book_domain.external

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.wmccd.book_domain.external.fakes.FakeAnalogueReporter
import com.wmccd.book_domain.external.fakes.FakeBookRepository
import com.wmccd.book_domain.external.usecases.UpdateBookUseCaseImpl
import com.wmccd.common_models_types.external.models.books.BookModel
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class UpdateBookUseCaseImplTest {

    @Test
    fun execute_update_analogueEntry() = runTest {

        //assemble
        val bookRepository = FakeBookRepository()
        val analogueReporter = FakeAnalogueReporter()
        val dispatcher = StandardTestDispatcher(testScheduler)
        val updateBookUseCaseImpl = UpdateBookUseCaseImpl(
            bookRepository = bookRepository,
            analogueReporter = analogueReporter,
            dispatcher = dispatcher
        )
        val bookModel = BookModel(
            id = 1L,
            author = "A",
            title = "B"
        )
        val expectedTrace = "Domain: book update"

        //act
        updateBookUseCaseImpl.execute(
            bookModel = bookModel
        )

        //assert
        assertEquals(expectedTrace, analogueReporter.lastInvoked)
    }

    @Test
    fun execute_insertWithInvalidData_exception() = runTest {

        //assemble
        val bookRepository = FakeBookRepository()
        val analogueReporter = FakeAnalogueReporter()
        val dispatcher = StandardTestDispatcher(testScheduler)
        val updateBookUseCaseImpl = UpdateBookUseCaseImpl(
            bookRepository = bookRepository,
            analogueReporter = analogueReporter,
            dispatcher = dispatcher
        )
        val bookModel = BookModel(
            id = 1L,
            author = "A",
            title = ""
        )
        val countDownLatch = CountDownLatch(1)
        val expectedCount = 0L

        //act
        try{
            updateBookUseCaseImpl.execute(
                bookModel = bookModel
            )
        }catch(ex: Exception){
            countDownLatch.countDown()
        }
        countDownLatch.await(100, TimeUnit.MILLISECONDS)

        //assert
        assertEquals(expectedCount, countDownLatch.count)
    }
}