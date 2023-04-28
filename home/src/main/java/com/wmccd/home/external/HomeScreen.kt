package com.wmccd.home.external

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.wmccd.home_presentation.external.homescreen.event.CounterEvent
import com.wmccd.home_presentation.external.homescreen.state.CounterStateWhenDisplaying
import com.wmccd.home_presentation.external.homescreen.CounterViewModel
import com.wmccd.home_presentation.external.homescreen.state.CounterState
import com.wmccd.home_presentation.external.homescreen.state.CounterStateWhenErroring
import com.wmccd.home_presentation.external.homescreen.state.CounterStateWhenLoading


@Composable
fun HomeScreen(counterViewModel: CounterViewModel){

    when(counterViewModel.currentState.value){
        is CounterState.Loading -> Loading(
            counterViewModel.uiLoadingState.value,
            counterViewModel::onEvent
        )
        is CounterState.Display -> Displaying(
            counterViewModel.uiDisplayState.value,
            counterViewModel::onEvent
        )
        is CounterState.Error -> Erroring(
            counterViewModel.uiErroringState.value,
            counterViewModel::onEvent
        )
    }
}


@Composable
private fun Loading(
    state: CounterStateWhenLoading,
    onEvent: (CounterEvent)-> Unit
){
    Text("Add a shimmer")
}

@Composable
private fun Erroring(
    state: CounterStateWhenErroring,
    onEvent: (CounterEvent)-> Unit
){
    Text("Add a uh-oh")

}

@Composable
private fun Displaying(
    state: CounterStateWhenDisplaying,
    onEvent: (CounterEvent)-> Unit
){
    Log.d("XXX", "Display $state")
    Column(){
        Text("Hello from Home")

        Text(
            state.bookCountLabel,
            color = state.bookCountLabelColour
        )
        Box(
            modifier = Modifier.clickable {
                onEvent(CounterEvent.OnBookCountTapped)
            }
        ) {
            Text("${state.bookCount}")
        }

        Text(
            state.recordCountLabel,
            color = state.recordCountLabelColour
        )
        Box(
            modifier = Modifier.clickable {
                onEvent(CounterEvent.OnRecordCountTapped)
            }
        ) {
            Text("${state.recordCount}")
        }
    }
}

//@Composable
//@Preview
//private fun Preview(){
//    class FakeCounterViewModel():CounterViewModel{
//        override val uiState: State<CombinedCounterState> = mutableStateOf(
//            CombinedCounterState(
//                state = CounterState.Loading,
//                loading = CounterStateWhenLoading(),
//                displaying = CounterStateWhenDisplaying(
//                    title = "The Mighty Bobbins Rides Again",
//                    bookCountLabel = "Feel the bookish girth",
//                    recordCountLabel = "Feel the record goodness",
//                ),
//                erroring = CounterStateWhenErroring()
//            )
//        )
//        override fun onEvent(notesEvent: CounterEvent) {}
//    }
//    val fakeCounterViewModel = FakeCounterViewModel()
//    Scaffold() {
//        HomeScreen(counterViewModel = fakeCounterViewModel )
//    }
//}