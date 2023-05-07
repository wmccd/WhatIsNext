package com.wmccd.home_presentation.external.homescreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wmccd.analogue_reporter.external.AnalogueAction
import com.wmccd.analogue_reporter.external.AnalogueReporter
import com.wmccd.book_domain.external.usecases.BookCountUseCase
import com.wmccd.common_models_types.external.models.weather.WeatherModel
import com.wmccd.configuration.external.RemoteConfiguration
import com.wmccd.configuration_keys.external.TextConfigurationKeys
import com.wmccd.home_presentation.external.homescreen.event.CounterEvent
import com.wmccd.home_presentation.external.homescreen.state.HomeState
import com.wmccd.home_presentation.external.homescreen.state.HomeStateWhenDisplaying
import com.wmccd.home_presentation.external.homescreen.state.HomeStateWhenErroring
import com.wmccd.home_presentation.external.homescreen.state.HomeStateWhenLoading
import com.wmccd.record_domain.external.usescases.RecordCountUseCase
import com.wmccd.weather_domain.external.WeatherAtLocationUseCase
import com.wmccd.weather_domain.external.WeatherLocationUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModelImpl(
    private val homeUseCases: HomeUseCases,
    private val analogueReporter: AnalogueReporter,
    private val remoteConfiguration: RemoteConfiguration
): HomeViewModel, ViewModel() {

    init{
        fetchWeatherAtLocation()
    }

    /** State **/

    private var _currentState:MutableState<HomeState> = mutableStateOf(HomeState.Loading)
    override val currentState: State<HomeState> = _currentState

    private val _uiLoadingState = mutableStateOf(
        HomeStateWhenLoading(
            message = remoteConfiguration.fetch(TextConfigurationKeys.ScreenLoading.loadingMessage, "")
        )
    )
    override val uiLoadingState: State<HomeStateWhenLoading> = _uiLoadingState

    private val _uiDisplayingState = mutableStateOf(
        HomeStateWhenDisplaying(
            title = remoteConfiguration.fetch(TextConfigurationKeys.HomeScreen.title, ""),
            bookCountLabel = remoteConfiguration.fetch(TextConfigurationKeys.HomeScreen.bookCountLabel, ""),
            recordCountLabel = remoteConfiguration.fetch(TextConfigurationKeys.HomeScreen.recordCountLabel, ""),
            buttonLabel = remoteConfiguration.fetch(TextConfigurationKeys.HomeScreen.changeColorButtonLabel, ""),
        )
    )
    override val uiDisplayState: State<HomeStateWhenDisplaying> = _uiDisplayingState

    private val _uiErroringState = mutableStateOf(
        HomeStateWhenErroring(
            message = remoteConfiguration.fetch(TextConfigurationKeys.ScreenErroring.erroringMessage, "")
        )
    )
    override val uiErroringState: State<HomeStateWhenErroring> = _uiErroringState

    private val bookCountObserverJob = viewModelScope.launch {
        homeUseCases.bookCountUseCase.execute().collect{

            analogueReporter.report(
                action = AnalogueAction.Trace(
                    tag = "${this::class.simpleName}",
                    whatHappened = "Presentation: book count",
                    key = "count",
                    value = "it"
                )
            )

            _currentState.value = HomeState.Displaying
            _uiDisplayingState.value = _uiDisplayingState.value.copy(
                bookCount = it
            )
        }
    }

    private val recordCountObserverJob = viewModelScope.launch {
        homeUseCases.recordCountUseCase.execute().collect{

            analogueReporter.report(
                action = AnalogueAction.Trace(
                    tag = "${this::class.simpleName}",
                    whatHappened = "Presentation: record count",
                    key = "count",
                    value = "it"
                )
            )

            _uiDisplayingState.value = _uiDisplayingState.value.copy(
                recordCount = it
            )
        }
    }

    private val weatherLocationObserverJob = viewModelScope.launch {
        homeUseCases.weatherLocationUseCase.execute().collect(){

            analogueReporter.report(
                action = AnalogueAction.Trace(
                    tag = "${this::class.simpleName}",
                    whatHappened = "Presentation: weather location",
                    key = "location",
                    value = "it"
                )
            )

            _uiDisplayingState.value = _uiDisplayingState.value.copy(
                weatherLocation = "Latitude:<${it.latitude}> Longitude:<${it.longitude}>"
            )
        }
    }

    private fun fetchWeatherAtLocation(){

        fun success(weatherModel: WeatherModel){
            _uiDisplayingState.value = _uiDisplayingState.value.copy(
                weatherAtLocation = "Updates available <${weatherModel.hourly.time.size}>"
            )
        }

        fun failure(){
            _uiDisplayingState.value = _uiDisplayingState.value.copy(
                weatherAtLocation = "No updates available"
            )
        }

        viewModelScope.launch {
            homeUseCases.weatherAtLocationUseCase.execute(
                success = ::success,
                failure = ::failure
            )
        }
    }



    /** Events **/
    override fun onEvent(counterEvent: CounterEvent) {
        when(counterEvent){
            CounterEvent.OnColorChangeButtonTapped -> handleOnColorChangeButtonTapped()
        }
    }

    private fun handleOnColorChangeButtonTapped() {

        analogueReporter.report(
            action = AnalogueAction.Trace(
                tag = "${this::class.simpleName}",
                whatHappened = "Presentation: colour change",
            )
        )

        _uiDisplayingState.value = _uiDisplayingState.value.copy(
            backgroundColor = randomColor()
        )
    }


    private fun randomColor(): Color{
        val colours = listOf<Color>(
            Color.DarkGray,
            Color.Blue,
            Color.Black,
            Color.Cyan,
            Color.Gray,
            Color.Green,
            Color.LightGray,
            Color.Magenta,
            Color.Red,
            Color.Yellow
        )
        val random = (colours.indices).random()
        return  colours[random]
    }

}