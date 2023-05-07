package com.wmccd.book_domain.external

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.wmccd.book_domain.external.fakes.FakeAnalogueReporter
import com.wmccd.book_domain.external.fakes.FakeBookRepository
import com.wmccd.book_domain.external.usecases.DeleteBookUseCaseImpl
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
class DeleteBookUseCaseImplTest {

    @Test
    fun execute_delete_analogueEntry() = runTest {

        //assemble
        val bookRepository = FakeBookRepository()
        val analogueReporter = FakeAnalogueReporter()
        val dispatcher = StandardTestDispatcher(testScheduler)
        val deleteBookUseCaseImpl = DeleteBookUseCaseImpl(
            bookRepository = bookRepository,
            analogueReporter = analogueReporter,
            dispatcher = dispatcher
        )
        val expectedTrace = "Domain: book delete"

        //act
        deleteBookUseCaseImpl.execute(id = 1L)

        //assert
        assertEquals(expectedTrace, analogueReporter.lastInvoked)
    }
}