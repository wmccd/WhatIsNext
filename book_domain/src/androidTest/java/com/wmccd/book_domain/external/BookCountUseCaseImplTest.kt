package com.wmccd.book_domain.external

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.wmccd.book_domain.external.fakes.FakeAnalogueReporter
import com.wmccd.book_domain.external.fakes.FakeBookRepository
import com.wmccd.book_domain.external.usecases.BookCountUseCaseImpl
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class BookCountUseCaseImplTest {

    @Test
    fun execute_delete_analogueEntry() = runTest {

        //assemble
        val bookRepository = FakeBookRepository()
        val analogueReporter = FakeAnalogueReporter()
        val dispatcher = StandardTestDispatcher(testScheduler)
        val bookCountUseCaseImpl = BookCountUseCaseImpl(
            bookRepository = bookRepository,
            analogueReporter = analogueReporter,
            dispatcher = dispatcher
        )
        val expected = 2

        //act
        val actual = bookCountUseCaseImpl.execute().first()

        //assert
        assertEquals(expected, actual)
    }
}