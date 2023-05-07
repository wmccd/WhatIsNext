package com.wmccd.book_domain.external

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.wmccd.book_domain.external.fakes.FakeAnalogueReporter
import com.wmccd.book_domain.external.fakes.FakeBookRepository
import com.wmccd.book_domain.external.fakes.FakeBookRepositoryWithNoBooks
import com.wmccd.book_domain.external.usecases.RandomBookUseCaseImpl
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
class RandomBookUseCaseImplTest {

    @Test
    fun execute_random_analogueEntry() = runTest {

        //assemble
        val bookRepository = FakeBookRepository()
        val analogueReporter = FakeAnalogueReporter()
        val dispatcher = StandardTestDispatcher(testScheduler)
        val randomBookUseCaseImpl = RandomBookUseCaseImpl(
            bookRepository = bookRepository,
            analogueReporter = analogueReporter,
            dispatcher = dispatcher
        )
        val expectedTrace = "Domain: random book"

        //act
        val bookModel = randomBookUseCaseImpl.execute()

        //assert
        assertEquals(expectedTrace, analogueReporter.lastInvoked)
        assertTrue(bookModel.id > -1)
    }

    @Test
    fun execute_randomWhenNoBooks_exception() = runTest {

        //assemble
        val bookRepository = FakeBookRepositoryWithNoBooks()
        val analogueReporter = FakeAnalogueReporter()
        val dispatcher = StandardTestDispatcher(testScheduler)
        val randomBookUseCaseImpl = RandomBookUseCaseImpl(
            bookRepository = bookRepository,
            analogueReporter = analogueReporter,
            dispatcher = dispatcher
        )
        val countDownLatch = CountDownLatch(1)
        val expectedCount = 0L

        //act
        try {
            randomBookUseCaseImpl.execute()
        }catch (ex: Exception){
            countDownLatch.countDown()
        }

        countDownLatch.await(100, TimeUnit.MILLISECONDS)

        //assert
        assertEquals(expectedCount, countDownLatch.count)
    }
}