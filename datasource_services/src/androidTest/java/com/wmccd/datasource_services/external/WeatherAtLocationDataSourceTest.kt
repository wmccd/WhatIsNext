package com.wmccd.datasource_services.external

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.wmccd.common_models_types.external.models.request.RequestModel
import com.wmccd.common_models_types.external.types.request.RequestContentType
import com.wmccd.common_models_types.external.types.request.RequestMethodType
import com.wmccd.datasource_services.external.fakes.FakeAnalogueReporter
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class WeatherAtLocationDataSourceTest {

    @Test
    fun testthus()= runTest{

        //assemble
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
            success = {
                //assert
                Log.d("XXX", "SUCCESS $it") },
            failure = { code: Int, message: String ->
                //assert
                Log.d("XXX", "FAILURE <$code> <$message>")
            }
        )
        val weatherAtLocationDataSource = WeatherAtLocationDataSourceImpl(
            analogueReporter = FakeAnalogueReporter()
        )

        //act
        weatherAtLocationDataSource.weather(
            requestModel = requestModel
        )

        //assert
        CountDownLatch(1).await(3, TimeUnit.SECONDS)

    }
}