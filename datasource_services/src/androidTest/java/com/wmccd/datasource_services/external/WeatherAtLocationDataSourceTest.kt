package com.wmccd.datasource_services.external

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.wmccd.common_models_types.external.models.request.RequestModel
import com.wmccd.common_models_types.external.types.ResponseType
import com.wmccd.common_models_types.external.types.request.RequestContentType
import com.wmccd.common_models_types.external.types.request.RequestMethodType
import com.wmccd.datasource_services.external.fakes.FakeAnalogueReporter
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class WeatherAtLocationDataSourceTest {

    @Test
    fun weather_validRequest_successInvoked()= runTest{

        //assemble
        val countDownLatch = CountDownLatch(1)
        fun successCallback(response: ResponseType){
            //assert
            Assert.assertTrue("incorrect type", response is ResponseType.WeatherAtLocationResponse)
            countDownLatch.countDown()
        }
        fun failureCallback(response: ResponseType.ErrorResponse){
            //assert
            Assert.fail("Unexpectedly in failure callback")
        }
        val queryParameters = HashMap<String,  String>()
        queryParameters["latitude"] = "54.50"
        queryParameters["longitude"] = "5.79"
        queryParameters["hourly"] = "temperature_2m,precipitation_probability"
        val requestModel = RequestModel(
            methodType = RequestMethodType.GET,
            contentType = RequestContentType.ApplicationJson,
            urlDomain = "https://api.open-meteo.com/v1/",
            urlPath = "forecast",
            headers = hashMapOf(),
            queryParameters = queryParameters,
            bodyMap = hashMapOf(),
            bodyJson = "{}",
            success = ::successCallback,
            failure = ::failureCallback
        )
        val weatherAtLocationDataSource = WeatherAtLocationDataSourceImpl(
            analogueReporter = FakeAnalogueReporter()
        )

        //act
        weatherAtLocationDataSource.weather(
            requestModel = requestModel
        )
        CountDownLatch(1).await(5, TimeUnit.SECONDS)

        //assert
        Assert.assertEquals(0L, countDownLatch.count)
    }

    @Test
    fun weather_invalidRequest_failureInvoked()= runTest{

        //assemble
        val countDownLatch = CountDownLatch(1)
        fun successCallback(response: ResponseType){
            //assert
            Assert.fail("Unexpectedly in success callback")
        }
        fun failureCallback(response: ResponseType.ErrorResponse){
            countDownLatch.countDown()
        }
        val requestModel = RequestModel(
            methodType = RequestMethodType.GET,
            contentType = RequestContentType.ApplicationJson,
            urlDomain = "https://api.open-meteo.com/v1/",
            urlPath = "BOBBINS",
            headers = hashMapOf(),
            queryParameters = hashMapOf(),
            bodyMap = hashMapOf(),
            bodyJson = "{}",
            success = ::successCallback,
            failure = ::failureCallback
        )
        val weatherAtLocationDataSource = WeatherAtLocationDataSourceImpl(
            analogueReporter = FakeAnalogueReporter()
        )

        //act
        weatherAtLocationDataSource.weather(
            requestModel = requestModel
        )
        CountDownLatch(1).await(5, TimeUnit.SECONDS)

        //assert
        Assert.assertEquals(0L, countDownLatch.count)
    }
}