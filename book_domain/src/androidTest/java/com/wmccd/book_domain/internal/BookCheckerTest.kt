package com.wmccd.book_domain.internal

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.wmccd.common_models_types.external.books.InvalidBookAuthorException
import com.wmccd.common_models_types.external.books.InvalidBookTitleException
import com.wmccd.common_models_types.external.models.books.BookModel
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
class BookCheckerTest {

    @Test
    fun check_blankAuthor_invalidAuthorException() = runTest{

        //assemble
        val checker = BookChecker()
        val model = BookModel(
            id = 1L,
            author = "",
            title = "A"
        )
        val countDownLatch = CountDownLatch(1)
        val expected = 0L

        //act
        try{
            checker.check(model)
        }catch (ex: InvalidBookAuthorException){
            countDownLatch.countDown()
        }
        countDownLatch.await(100, TimeUnit.MILLISECONDS)

        //assert
        assertEquals(expected, countDownLatch.count)
    }

    @Test
    fun check_blankTitle_invalidTitleException() = runTest{

        //assemble
        val checker = BookChecker()
        val model = BookModel(
            id = 1L,
            author = "Q",
            title = ""
        )
        val countDownLatch = CountDownLatch(1)
        val expected = 0L

        //act
        try{
            checker.check(model)
        }catch (ex: InvalidBookTitleException){
            countDownLatch.countDown()
        }
        countDownLatch.await(100, TimeUnit.MILLISECONDS)

        //assert
        assertEquals(expected, countDownLatch.count)
    }

    @Test
    fun check_populated_noException() = runTest{

        //assemble
        val checker = BookChecker()
        val model = BookModel(
            id = 1L,
            author = "Q",
            title = "E"
        )
        val countDownLatch = CountDownLatch(1)
        val expected = 1L

        //act
        try{
            checker.check(model)
        }catch (ex: Exception){
            countDownLatch.countDown()
        }
        countDownLatch.await(100, TimeUnit.MILLISECONDS)

        //assert
        assertEquals(expected, countDownLatch.count)
    }

}