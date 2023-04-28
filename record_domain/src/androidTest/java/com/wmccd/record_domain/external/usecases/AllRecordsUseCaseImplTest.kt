package com.wmccd.record_domain.external.usecases

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.wmccd.record_domain.external.fakes.FakeAnalogueReporter
import com.wmccd.record_domain.external.fakes.FakeRecordRepository
import com.wmccd.record_domain.external.usescases.AllRecordsUseCaseImpl
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class AllRecordsUseCaseImplTest {

    @Test
    fun execute_flow_analogueEntry() = runTest {

        //assemble
        val recordRepository = FakeRecordRepository()
        val analogueReporter = FakeAnalogueReporter()
        val dispatcher = StandardTestDispatcher(testScheduler)
        val allRecordsUseCaseImpl = AllRecordsUseCaseImpl(
            recordRepository = recordRepository,
            analogueReporter = analogueReporter,
            dispatcher = dispatcher
        )
        val expectedTrace = "Domain: record flow updates"

        //act
        allRecordsUseCaseImpl.execute().first()

        //assert
        assertEquals(expectedTrace, analogueReporter.lastInvoked)

    }
}