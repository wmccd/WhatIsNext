package com.wmccd.record_domain.external.usecases

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.wmccd.record_domain.external.fakes.FakeAnalogueReporter
import com.wmccd.record_domain.external.fakes.FakeRecordRepository
import com.wmccd.record_domain.external.usescases.DeleteRecordUseCaseImpl
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
class DeleteRecordUseCaseImplTest {

    @Test
    fun execute_delete_analogueEntry() = runTest {

        //assemble
        val recordRepository = FakeRecordRepository()
        val analogueReporter = FakeAnalogueReporter()
        val dispatcher = StandardTestDispatcher(testScheduler)
        val deleteRecordUseCaseImpl = DeleteRecordUseCaseImpl(
            recordRepository = recordRepository,
            analogueReporter = analogueReporter,
            dispatcher = dispatcher
        )
        val expectedTrace = "Domain: record delete"

        //act
        deleteRecordUseCaseImpl.execute(id = 1L)

        //assert
        assertEquals(expectedTrace, analogueReporter.lastInvoked)
    }
}