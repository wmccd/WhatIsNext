package com.wmccd.analogue_reporter

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.wmccd.analogue_reporter.external.AnalogueAction
import com.wmccd.analogue_reporter.external.AnalogueReporterImpl
import org.junit.Assert

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class AnalogueReporterImplTest {

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.wmccd.analogue_reporter.test", appContext.packageName)
    }


    @Test
    fun xx() {

        //assemble
        val analogueReporter = AnalogueReporterImpl()
        analogueReporter.setup(hashMapOf(), true)
        val action = AnalogueAction.Event(
            whatHappened = "Bobbins Happened"
        )
        val expected = ""

        //act
        analogueReporter.report(action = action)

        //assert
        Assert.assertEquals(expected, analogueReporter.testingAction )

    }
}