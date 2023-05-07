package com.wmccd.weather_domain.external

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
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
class WeatherAtLocationImplTest {
    @Test
    fun execute() = runTest{
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.wmccd.weather_domain.test", appContext.packageName)

//        val queryParameters = HashMap<String,  String>()
//        queryParameters["latitude"] = "54.50"
//        queryParameters["longitude"] = "5.79"
//        queryParameters["hourly"] = "temperature_2m,precipitation_probability"
        //latitude = "54.50",
//            longitude = "5.79",
//            hourly = "temperature_2m,precipitation_probability"
//
//        val requestModel = RequestModel(
//            methodType = RequestMethodType.GET,
//            contentType = RequestContentType.ApplicationJson,
//            urlDomain = "https://api.open-meteo.com/v1/",
//            urlPath = "forecast",
//            headers = hashMapOf(),
//            queryParameters = queryParameters,
//            bodyMap = hashMapOf(),
//            bodyJson = "{}",
//            success = {
//                Log.d("XXX", "SUCCESS") },
//            failure = {
//                    i: Int, s: String ->
//                Log.d("XXX", "FAILURE")
//            }
//        )

//        val weatherLocationRepository = mockk<WeatherLocationRepository>()
//        val weatherAtLocationRepository = mockk<WeatherAtLocationRepository>()
//        val analogueReporter = mockk<AnalogueReporter>()
//        every{weatherLocationRepository.location} returns flow{emit( LocationModel(
//            latitude = 54.50f,
//            longitude = 5.79f
//        ) )}
//
//
//        WeatherAtLocationUseCaseImpl(
//            weatherLocationRepository = weatherLocationRepository,
//            weatherAtLocationRepository = weatherAtLocationRepository,
//            analogueReporter = analogueReporter,
//        ).execute(
//           success = {WeatherModel()},
//           failure = {}
//        )

    }
}