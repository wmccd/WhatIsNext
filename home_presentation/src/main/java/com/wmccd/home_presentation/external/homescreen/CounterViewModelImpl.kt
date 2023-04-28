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
import com.wmccd.home_presentation.external.homescreen.event.CounterEvent
import com.wmccd.home_presentation.external.homescreen.state.CounterState
import com.wmccd.home_presentation.external.homescreen.state.CounterStateWhenDisplaying
import com.wmccd.home_presentation.external.homescreen.state.CounterStateWhenErroring
import com.wmccd.home_presentation.external.homescreen.state.CounterStateWhenLoading
import com.wmccd.record_domain.external.usescases.RecordCountUseCase
import kotlinx.coroutines.launch

class CounterViewModelImpl(
    private val bookCountUseCase: BookCountUseCase,
    private val recordCountUseCase: RecordCountUseCase,
    private val analogueReporter: AnalogueReporter,
): CounterViewModel, ViewModel() {

    /** State **/
    private var _currentState:MutableState<CounterState> = mutableStateOf(CounterState.Loading)
    override val currentState: State<CounterState> = _currentState

    private val _uiLoadingState = mutableStateOf(
        CounterStateWhenLoading(
            message = "Loading"
        )
    )
    override val uiLoadingState: State<CounterStateWhenLoading> = _uiLoadingState

    private val _uiDisplayingState = mutableStateOf(
        CounterStateWhenDisplaying(
            title = "The Mighty Bobbins Rides Again",
            bookCountLabel = "Feel the bookish girth",
            recordCountLabel = "Feel the record goodness",
        )
    )
    override val uiDisplayState: State<CounterStateWhenDisplaying> = _uiDisplayingState

    private val _uiErroringState = mutableStateOf(
        CounterStateWhenErroring(
            message = "Uh-oh...something went wrong"
        )
    )
    override val uiErroringState: State<CounterStateWhenErroring> = _uiErroringState


    private val bookCountObserver = viewModelScope.launch {
        bookCountUseCase.execute().collect{

            analogueReporter.report(
                action = AnalogueAction.Trace(
                    tag = "${this::class.simpleName}",
                    whatHappened = "Presentation: book count",
                    key = "count",
                    value = "it"
                )
            )

            _currentState.value = CounterState.Display
            _uiDisplayingState.value = _uiDisplayingState.value.copy(
                bookCount = it
            )
        }
    }

    private val recordCountObserver = viewModelScope.launch {
        recordCountUseCase.execute().collect{

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


    /** Events **/
    override fun onEvent(counterEvent: CounterEvent) {
        when(counterEvent){
            CounterEvent.OnBookCountTapped -> handleOnBookCountTapped()
            CounterEvent.OnRecordCountTapped -> hanOnRecordCountTapped()
        }
    }

    private fun hanOnRecordCountTapped() {

        analogueReporter.report(
            action = AnalogueAction.Trace(
                tag = "${this::class.simpleName}",
                whatHappened = "Presentation: tapped record count",
            )
        )

        _uiDisplayingState.value = _uiDisplayingState.value.copy(
            recordCountLabelColour = randomColor()
        )
    }

    private fun handleOnBookCountTapped() {

        analogueReporter.report(
            action = AnalogueAction.Trace(
                tag = "${this::class.simpleName}",
                whatHappened = "Presentation: tapped book count",
            )
        )

        _uiDisplayingState.value = _uiDisplayingState.value.copy(
            bookCountLabelColour = randomColor()
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