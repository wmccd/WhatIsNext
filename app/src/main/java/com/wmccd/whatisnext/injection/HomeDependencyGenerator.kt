package com.wmccd.whatisnext.injection

import com.wmccd.book_datasource.external.BookDataSourceImpl
import com.wmccd.book_datasource.external.RecordDataSourceImpl
import com.wmccd.book_domain.external.usecases.BookCountUseCase
import com.wmccd.book_domain.external.usecases.BookCountUseCaseImpl
import com.wmccd.book_repository.external.BookRepositoryImpl
import com.wmccd.datasource_datastore.external.WeatherLocationDataSourceImp
import com.wmccd.datasource_services.external.WeatherAtLocationDataSourceImpl
import com.wmccd.home_presentation.external.homescreen.HomeUseCases
import com.wmccd.home_presentation.external.homescreen.HomeViewModelImpl
import com.wmccd.record_domain.external.usescases.RecordCountUseCase
import com.wmccd.record_domain.external.usescases.RecordCountUseCaseImpl
import com.wmccd.record_repository.external.RecordRepositoryImpl
import com.wmccd.weather_domain.external.WeatherAtLocationUseCase
import com.wmccd.weather_domain.external.WeatherAtLocationUseCaseImpl
import com.wmccd.weather_domain.external.WeatherLocationUseCase
import com.wmccd.weather_domain.external.WeatherLocationUseCaseImpl
import com.wmccd.weather_repository.external.WeatherAtLocationRepositoryImpl
import com.wmccd.weather_repository.external.WeatherLocationRepositoryImpl
import com.wmccd.whatisnext.MyApp
import kotlinx.coroutines.Dispatchers

class HomeDependencyGenerator {

    fun generate(): HomeViewModelImpl{

        val homeUseCases = HomeUseCases(
            bookCountUseCase = generateBookCountUseCase(),
            recordCountUseCase = generateRecordCountUseCase(),
            weatherLocationUseCase = generateWeatherLocationUseCase(),
            weatherAtLocationUseCase = generateWeatherAtLocationUseCase(),
        )

        return HomeViewModelImpl(
            homeUseCases = homeUseCases,
            analogueReporter = MyApp.appAnalogueReporter,
            remoteConfiguration = MyApp.appRemoteConfiguration
        )
    }

    private fun generateBookCountUseCase(): BookCountUseCase {
        val bookDataSource = BookDataSourceImpl(
            context = MyApp.context,
            analogueReporter = MyApp.appAnalogueReporter
        )

        val bookRepository = BookRepositoryImpl(
            dataSource = bookDataSource,
            analogueReporter = MyApp.appAnalogueReporter,
            dispatcher = Dispatchers.IO
        )

        return BookCountUseCaseImpl(
            bookRepository = bookRepository,
            analogueReporter = MyApp.appAnalogueReporter,
            dispatcher = Dispatchers.Default
        )
    }

    private fun generateRecordCountUseCase(): RecordCountUseCase {
        val recordDataSource = RecordDataSourceImpl(
            context = MyApp.context,
            analogueReporter = MyApp.appAnalogueReporter
        )

        val recordRepository = RecordRepositoryImpl(
            dataSource = recordDataSource,
            analogueReporter = MyApp.appAnalogueReporter,
            dispatcher = Dispatchers.IO
        )

        return RecordCountUseCaseImpl(
            recordRepository = recordRepository,
            analogueReporter = MyApp.appAnalogueReporter,
            dispatcher = Dispatchers.Default
        )
    }

    private fun generateWeatherLocationUseCase() : WeatherLocationUseCase {
        val weatherLocationDataSource = WeatherLocationDataSourceImp(
            context = MyApp.context,
            analogueReporter = MyApp.appAnalogueReporter
        )
        val weatherLocationRepository = WeatherLocationRepositoryImpl(
            dataSource = weatherLocationDataSource,
            analogueReporter = MyApp.appAnalogueReporter,
            dispatcher = Dispatchers.IO
        )
        return WeatherLocationUseCaseImpl(
            weatherLocationRepository = weatherLocationRepository,
            analogueReporter =MyApp.appAnalogueReporter,
            dispatcher = Dispatchers.Default
        )
    }

    private fun generateWeatherAtLocationUseCase() : WeatherAtLocationUseCase {
        val weatherAtLocationDataSource = WeatherAtLocationDataSourceImpl(
            analogueReporter = MyApp.appAnalogueReporter
        )
        val weatherAtLocationRepository = WeatherAtLocationRepositoryImpl(
            dataSource = weatherAtLocationDataSource,
            analogueReporter = MyApp.appAnalogueReporter,
            dispatcher = Dispatchers.IO
        )
        return WeatherAtLocationUseCaseImpl(
            weatherLocationUseCase = generateWeatherLocationUseCase(),
            weatherAtLocationRepository = weatherAtLocationRepository,
            analogueReporter =MyApp.appAnalogueReporter,
            dispatcher = Dispatchers.Default
        )
    }
}