package com.wmccd.record_domain.external.usecases

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.wmccd.common_models.external.records.RecordModel
import com.wmccd.record_domain.external.fakes.FakeAnalogueReporter
import com.wmccd.record_domain.external.fakes.FakeRecordRepository
import com.wmccd.record_domain.external.usescases.UpdateRecordUseCaseImpl
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class UpdateRecordUseCaseImplTest {

    @Test
    fun execute_update_analogueEntry() = runTest {

        //assemble
        val recordRepository = FakeRecordRepository()
        val analogueReporter = FakeAnalogueReporter()
        val dispatcher = StandardTestDispatcher(testScheduler)
        val updateRecordUseCaseImpl = UpdateRecordUseCaseImpl(
            recordRepository = recordRepository,
            analogueReporter = analogueReporter,
            dispatcher = dispatcher
        )
        val recordModel = RecordModel(
            id = 1L,
            act = "A",
            title = "B"
        )
        val expectedTrace = "Domain: record update"

        //act
        updateRecordUseCaseImpl.execute(
            recordModel = recordModel
        )

        //assert
        assertEquals(expectedTrace, analogueReporter.lastInvoked)
    }

    @Test
    fun execute_insertWithInvalidData_exception() = runTest {

        //assemble
        val recordRepository = FakeRecordRepository()
        val analogueReporter = FakeAnalogueReporter()
        val dispatcher = StandardTestDispatcher(testScheduler)
        val updateRecordUseCaseImpl = UpdateRecordUseCaseImpl(
            recordRepository = recordRepository,
            analogueReporter = analogueReporter,
            dispatcher = dispatcher
        )
        val recordModel = RecordModel(
            id = 1L,
            act = "A",
            title = ""
        )
        val countDownLatch = CountDownLatch(1)
        val expectedCount = 0L

        //act
        try{
            updateRecordUseCaseImpl.execute(
                recordModel = recordModel
            )
        }catch(ex: Exception){
            countDownLatch.countDown()
        }
        countDownLatch.await(100, TimeUnit.MILLISECONDS)

        //assert
        assertEquals(expectedCount, countDownLatch.count)
    }
}