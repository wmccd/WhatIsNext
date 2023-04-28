//package com.wmccd.record_domain.external.usecases
//
//import androidx.test.ext.junit.runners.AndroidJUnit4
//import com.wmccd.record_domain.external.fakes.FakeAnalogueReporter
//import com.wmccd.record_domain.external.fakes.FakeRecentRandomRecordRepository
//import com.wmccd.record_domain.external.fakes.FakeRecentRandomRecordSettingsRepository
//import com.wmccd.record_domain.external.fakes.FakeRecordRepository
//import com.wmccd.record_domain.external.fakes.FakeRecordRepositoryWithNoRecords
//import com.wmccd.record_domain.external.usescases.RandomRecordUseCaseImpl
//import com.wmccd.record_repository.external.RecentRandomRecordRepository
//import kotlinx.coroutines.test.StandardTestDispatcher
//import kotlinx.coroutines.test.runTest
//
//import org.junit.Test
//import org.junit.runner.RunWith
//
//import org.junit.Assert.*
//import java.lang.Exception
//import java.util.concurrent.CountDownLatch
//import java.util.concurrent.TimeUnit
//
///**
// * Instrumented test, which will execute on an Android device.
// *
// * See [testing documentation](http://d.android.com/tools/testing).
// */
//@RunWith(AndroidJUnit4::class)
//class RandomRecordUseCaseImplTest {
//
//
//
////
////    @Test
////    fun execute_random_analogueEntry() = runTest {
////
////        //assemble
////        val recordRepository = FakeRecordRepository()
////        val analogueReporter = FakeAnalogueReporter()
////        val recentRandomRecordsRepository = FakeRecentRandomRecordRepository()
////        val recentRandomRecordSettingsRepository =  FakeRecentRandomRecordSettingsRepository()
////        val dispatcher = StandardTestDispatcher(testScheduler)
////        val randomRecordUseCaseImpl = RandomRecordUseCaseImpl(
////            recordRepository = recordRepository,
////            recentRandomRecordsRepository = recentRandomRecordsRepository,
////            recentRandomRecordSettingsRepository = recentRandomRecordSettingsRepository,
////            analogueReporter = analogueReporter,
////            dispatcher = dispatcher
////        )
////        val expectedTrace = "Domain: random record"
////
////        //act
////        val recordModel = randomRecordUseCaseImpl.execute()
////
////        //assert
////        assertEquals(expectedTrace, analogueReporter.lastInvoked)
////        assertTrue(recordModel.id > -1)
////    }
////
////    @Test
////    fun execute_randomWhenNoRecords_exception() = runTest {
////
////        //assemble
////        val recordRepository = FakeRecordRepositoryWithNoRecords()
////        val analogueReporter = FakeAnalogueReporter()
////        val recentRandomRecordsRepository = FakeRecentRandomRecordRepository()
////        val recentRandomRecordSettingsRepository =  FakeRecentRandomRecordSettingsRepository()
////        val dispatcher = StandardTestDispatcher(testScheduler)
////        val randomRecordUseCaseImpl = RandomRecordUseCaseImpl(
////            recordRepository = recordRepository,
////            recentRandomRecordsRepository = recentRandomRecordsRepository,
////            recentRandomRecordSettingsRepository = recentRandomRecordSettingsRepository,
////            analogueReporter = analogueReporter,
////            dispatcher = dispatcher
////        )
////        val countDownLatch = CountDownLatch(1)
////        val expectedCount = 0L
////
////        //act
////        try {
////            randomRecordUseCaseImpl.execute()
////        }catch (ex: Exception){
////            countDownLatch.countDown()
////        }
////
////        countDownLatch.await(100, TimeUnit.MILLISECONDS)
////
////        //assert
////        assertEquals(expectedCount, countDownLatch.count)
////    }
//
//
//}