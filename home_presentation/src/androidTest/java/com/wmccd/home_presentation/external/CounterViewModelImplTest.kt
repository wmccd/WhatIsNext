package com.wmccd.home_presentation.external

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.wmccd.home_presentation.external.fakes.FakeAnalogueReporter
import com.wmccd.home_presentation.external.fakes.FakeBookCountUseCase
import com.wmccd.home_presentation.external.fakes.FakeRecordCountUseCase
import com.wmccd.home_presentation.external.homescreen.event.CounterEvent
import com.wmccd.home_presentation.external.homescreen.CounterViewModelImpl
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
class CounterViewModelImplTest {

    @Test
    fun state_bookCount_correct()= runTest{

        //assemble
        val counterViewModelImpl = CounterViewModelImpl(
            bookCountUseCase = FakeBookCountUseCase(),
            recordCountUseCase = FakeRecordCountUseCase(),
            analogueReporter = FakeAnalogueReporter()
        )
        val expected = 763

        //act
        CountDownLatch(1).await(100, TimeUnit.MILLISECONDS)
        val actual = counterViewModelImpl.uiDisplayState.value.bookCount

        //assert
        assertEquals(expected, actual)
    }


    @Test
    fun state_recordCount_correct()= runTest{

        //assemble
        val counterViewModelImpl = CounterViewModelImpl(
            bookCountUseCase = FakeBookCountUseCase(),
            recordCountUseCase = FakeRecordCountUseCase(),
            analogueReporter = FakeAnalogueReporter()
        )
        val expected = 765

        //act
        CountDownLatch(1).await(100, TimeUnit.MILLISECONDS)
        val actual = counterViewModelImpl.uiDisplayState.value.recordCount

        //assert
        assertEquals(expected, actual)
    }

    @Test
    fun onEvent_recordCountTapped_handled()= runTest{

        //assemble
        val analogueReporter = FakeAnalogueReporter()

        val counterViewModelImpl = CounterViewModelImpl(
            bookCountUseCase = FakeBookCountUseCase(),
            recordCountUseCase = FakeRecordCountUseCase(),
            analogueReporter = analogueReporter
        )
        val expected = "Presentation: tapped record count"

        //act
        counterViewModelImpl.onEvent(
            counterEvent = CounterEvent.OnRecordCountTapped
        )

        //assert
        assertTrue(analogueReporter.lastInvokedList.contains(expected))
    }

    @Test
    fun onEvent_bookCountTapped_handled()= runTest{

        //assemble
        val analogueReporter = FakeAnalogueReporter()

        val counterViewModelImpl = CounterViewModelImpl(
            bookCountUseCase = FakeBookCountUseCase(),
            recordCountUseCase = FakeRecordCountUseCase(),
            analogueReporter = analogueReporter
        )
        val expected = "Presentation: tapped book count"

        //act
        counterViewModelImpl.onEvent(
            counterEvent = CounterEvent.OnBookCountTapped
        )

        //assert
        assertTrue(analogueReporter.lastInvokedList.contains(expected))
    }
}