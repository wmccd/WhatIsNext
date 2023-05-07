package com.wmccd.home_presentation.external

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.wmccd.home_presentation.external.fakes.FakeAnalogueReporter
import com.wmccd.home_presentation.external.fakes.FakeBookCountUseCase
import com.wmccd.home_presentation.external.fakes.FakeRecordCountUseCase
import com.wmccd.home_presentation.external.fakes.FakeRemoteConfiguration
import com.wmccd.home_presentation.external.fakes.FakeWeatherAtLocationUseCase
import com.wmccd.home_presentation.external.fakes.FakeWeatherLocationUseCase
import com.wmccd.home_presentation.external.homescreen.HomeUseCases
import com.wmccd.home_presentation.external.homescreen.HomeViewModelImpl
import com.wmccd.home_presentation.external.homescreen.event.CounterEvent
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
class HomeViewModelImplTest {

    private val homeUseCases = HomeUseCases(
        bookCountUseCase = FakeBookCountUseCase(),
        recordCountUseCase = FakeRecordCountUseCase(),
        weatherLocationUseCase = FakeWeatherLocationUseCase(),
        weatherAtLocationUseCase = FakeWeatherAtLocationUseCase()
    )

    @Test
    fun state_bookCount_correct()= runTest{

        //assemble
        val counterViewModelImpl = HomeViewModelImpl(
            homeUseCases = homeUseCases,
            analogueReporter = FakeAnalogueReporter(),
            remoteConfiguration = FakeRemoteConfiguration()
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
        val counterViewModelImpl = HomeViewModelImpl(
            homeUseCases = homeUseCases,
            analogueReporter = FakeAnalogueReporter(),
            remoteConfiguration = FakeRemoteConfiguration()
        )
        val expected = 765

        //act
        CountDownLatch(1).await(100, TimeUnit.MILLISECONDS)
        val actual = counterViewModelImpl.uiDisplayState.value.recordCount

        //assert
        assertEquals(expected, actual)
    }


    @Test
    fun onEvent_colourButtonTapped_handled()= runTest{

        //assemble
        val analogueReporter = FakeAnalogueReporter()

        val counterViewModelImpl = HomeViewModelImpl(
            homeUseCases = homeUseCases,
            analogueReporter = analogueReporter,
            remoteConfiguration = FakeRemoteConfiguration()
        )
        val expected = "Presentation: colour change"

        //act
        counterViewModelImpl.onEvent(
            counterEvent = CounterEvent.OnColorChangeButtonTapped
        )

        //assert
        assertTrue(analogueReporter.lastInvokedList.contains(expected))
    }
}