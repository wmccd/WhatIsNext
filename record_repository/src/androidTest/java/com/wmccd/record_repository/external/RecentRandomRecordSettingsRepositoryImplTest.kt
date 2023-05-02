package com.wmccd.record_repository.external

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.wmccd.record_repository.external.fakes.FakeAnalogueReporter
import com.wmccd.record_repository.external.fakes.FakeRecentRandomRecordSettingsDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class RecentRandomRecordSettingsRepositoryImplTest {

    private val appContext = InstrumentationRegistry.getInstrumentation().targetContext


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun recentRandomRecordsWindowSize_setAndRetrieve_correctValue() = runTest{

        //assemble
        val dataSource = FakeRecentRandomRecordSettingsDataSource()
        val analogueReporter = FakeAnalogueReporter()
        val dispatcher = StandardTestDispatcher(testScheduler)
        val repository = RecentRandomRecordSettingsRepositoryImpl(
            dataSource = dataSource,
            analogueReporter = analogueReporter,
            dispatcher = dispatcher
        )
        val expected = 10

        //act
        repository.recentRandomRecordsWindowSize(expected)
        val actual = repository.recentRandomRecordsWindowSize.first()

        //assert
        Assert.assertEquals(expected, actual)
    }

}