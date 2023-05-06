package com.wmccd.datasource_services.internal.services.converters

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.wmccd.datasource_services.internal.services.weather.WeatherCall
import com.wmccd.datasource_services.internal.services.weather.models.HourlyResponse
import com.wmccd.datasource_services.internal.services.weather.models.HourlyUnitsResponse
import com.wmccd.datasource_services.internal.services.weather.models.WeatherResponse
import kotlinx.coroutines.test.runTest
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
class ConvertWeatherServiceAndCommonModelsTest {

    private val hourlyResponse = HourlyResponse(
        precipitationProbability = listOf(1, 2, 3),
        temperature2m = listOf(1.1, 2.2, 3.3 ),
        time = listOf("A", "B", "C")
    )

    private val hourlyUnitsResponse = HourlyUnitsResponse(
        precipitationProbability = "X",
        temperature2m = "Y",
        time = "Z"
    )

    private val weatherResponse = WeatherResponse(
        elevation = 4.0,
        generationtimeMs = 5.0,
        hourly = hourlyResponse,
        hourlyUnits = hourlyUnitsResponse,
        latitude = 6.0,
        longitude = 7.0,
        timezone = "M",
        timezoneAbbreviation = "N",
        utcOffsetSeconds = 8
    )

    @Test
    fun convert_emptyHourly_empty() = runTest{

        //assemble
        val converter = ConvertWeatherServiceAndCommonModels()

        //act
        val actual = converter.convert(weatherResponse)

        //assert
        assertEquals(weatherResponse.elevation, actual.elevation, 0.0)
        assertEquals(weatherResponse.generationtimeMs, actual.generationtimeMs, 0.0)
        assertEquals(weatherResponse.latitude, actual.latitude, 0.0)
        assertEquals(weatherResponse.longitude, actual.longitude, 0.0)
        assertEquals(weatherResponse.timezone, actual.timezone)
        assertEquals(weatherResponse.timezoneAbbreviation, actual.timezoneAbbreviation)
        assertEquals(weatherResponse.utcOffsetSeconds, actual.utcOffsetSeconds)
        assertEquals(weatherResponse.hourlyUnits.time, actual.hourlyUnits.time,)
        assertEquals(weatherResponse.hourlyUnits.temperature2m, actual.hourlyUnits.temperature2m,)
        assertEquals(weatherResponse.hourlyUnits.precipitationProbability, actual.hourlyUnits.precipitationProbability,)
        assertEquals(weatherResponse.hourly.time.size, actual.hourly.time.size)
        assertEquals(weatherResponse.hourly.precipitationProbability.size, actual.hourly.precipitationProbability.size)
        assertEquals(weatherResponse.hourly.temperature2m.size, actual.hourly.temperature2m.size)
    }
}