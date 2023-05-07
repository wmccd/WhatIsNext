package com.wmccd.datasource_datastore.external

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.wmccd.datasource_datastore.external.fakes.FakeAnalogueReporter
import kotlinx.coroutines.flow.first
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
class RecentRandomRecordSettingsImplTest {

    private val appContext = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun recentRandomRecordsWindowSize_set_retrieveCorrectValue() = runTest {

        //assemble
        val analogueReporter = FakeAnalogueReporter()
        val recentRandomRecordSettingsImpl = RecentRandomRecordSettingsDataSourceImpl(
            context = appContext,
            analogueReporter = analogueReporter
        )
        val expected = 7

        //act
        recentRandomRecordSettingsImpl.recentRandomRecordsWindowSize(size = expected)
        val actual = recentRandomRecordSettingsImpl.recentRandomRecordsWindowSize.first()

        //assert
        assertEquals(expected, actual)
    }
}