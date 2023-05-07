package com.wmccd.record_domain.external.usecases

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.wmccd.record_domain.external.fakes.FakeAnalogueReporter
import com.wmccd.record_domain.external.fakes.FakeRecordRepository
import com.wmccd.record_domain.external.usescases.RecordCountUseCaseImpl
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
class RecordCountUseCaseImplTest {

    @Test
    fun execute_delete_analogueEntry() = runTest {

        //assemble
        val recordRepository = FakeRecordRepository()
        val analogueReporter = FakeAnalogueReporter()
        val dispatcher = StandardTestDispatcher(testScheduler)
        val recordCountUseCase = RecordCountUseCaseImpl(
            recordRepository = recordRepository,
            analogueReporter = analogueReporter,
            dispatcher = dispatcher
        )
        val expected = 2

        //act
        val actual = recordCountUseCase.execute().first()

        //assert
        assertEquals(expected, actual)
    }
}