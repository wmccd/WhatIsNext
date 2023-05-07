package com.wmccd.home.external

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import com.wmccd.common_ui.external.values.Padding
import com.wmccd.common_ui.external.views.LabelAndValue
import com.wmccd.home_presentation.external.homescreen.HomeViewModel
import com.wmccd.home_presentation.external.homescreen.event.CounterEvent
import com.wmccd.home_presentation.external.homescreen.state.HomeState
import com.wmccd.home_presentation.external.homescreen.state.HomeStateWhenDisplaying
import com.wmccd.home_presentation.external.homescreen.state.HomeStateWhenErroring
import com.wmccd.home_presentation.external.homescreen.state.HomeStateWhenLoading

const val HomeScreenTestTag = "HomeScreen"

@Composable
fun HomeScreen(
    counterViewModel: HomeViewModel
){

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(counterViewModel.uiDisplayState.value.backgroundColor)
            .testTag("$HomeScreenTestTag loading"),
    ) {

        when (counterViewModel.currentState.value) {
            is HomeState.Loading -> Loading(
                counterViewModel.uiLoadingState.value
            )

            is HomeState.Displaying -> Displaying(
                counterViewModel.uiDisplayState.value,
                counterViewModel::onEvent
            )

            is HomeState.Erroring -> Erroring(
                counterViewModel.uiErroringState.value
            )
        }
    }
}


@Composable
private fun Loading(
    state: HomeStateWhenLoading
){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center

    ) {
        Spacer(modifier = Modifier.height(Padding.xlarge))
        Text(
            text = state.message,
            modifier = Modifier
                .testTag("$HomeScreenTestTag loading=${state.message}"),
            style = MaterialTheme.typography.headlineMedium
        )
    }
}

@Composable
private fun Erroring(
    state: HomeStateWhenErroring
){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center

    ) {
        Spacer(modifier = Modifier.height(Padding.xlarge))
        Text(
            text = state.message,
            modifier = Modifier
                .testTag("$HomeScreenTestTag erroring=${state.message}"),
            style = MaterialTheme.typography.headlineMedium
        )
    }
}


@Composable
private fun Displaying(
    state: HomeStateWhenDisplaying,
    onEvent: (CounterEvent)-> Unit
){
    Column(
        modifier = Modifier
            .padding(horizontal = Padding.large)
            .testTag("$HomeScreenTestTag displaying"),

    ) {

        Column(modifier = Modifier
            .fillMaxWidth()
            .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(Padding.xlarge))
            Text(
                text = state.title,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(Padding.xlarge))
            LabelAndValue(
                labelText = state.bookCountLabel,
                valueText = state.bookCount.toString()
            )
            Spacer(modifier = Modifier.height(Padding.xlarge))
            LabelAndValue(
                labelText = state.recordCountLabel,
                valueText = state.recordCount.toString()
            )
            Spacer(modifier = Modifier.height(Padding.xlarge))

            Text(text = state.weatherLocation)
            Text(text = state.weatherAtLocation)
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                modifier = Modifier.testTag("$HomeScreenTestTag button"),
                onClick = {
                    onEvent(CounterEvent.OnColorChangeButtonTapped)
                }
            ) {
                Text(state.buttonLabel)
            }
        }

    }
}

@Composable
@Preview(name = "Light Mode")
@Preview(name = "Dark Mode", showBackground = true,  uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "FontScale", fontScale = 2f)
@Preview(name = "System", showSystemUi = true)
private fun PreviewLoading(){
    class FakeHomeViewModel():HomeViewModel{

        override val currentState: State<HomeState> = mutableStateOf(HomeState.Loading)
        override val uiLoadingState: State<HomeStateWhenLoading> = mutableStateOf(
            HomeStateWhenLoading(
                message = "Loading this thing..."
            )
        )
        override val uiDisplayState: State<HomeStateWhenDisplaying> = mutableStateOf(HomeStateWhenDisplaying())
        override val uiErroringState: State<HomeStateWhenErroring> = mutableStateOf(HomeStateWhenErroring())
        override fun onEvent(event: CounterEvent) {}
    }
    val fakeCounterViewModel = FakeHomeViewModel()

    Surface() {
        HomeScreen(counterViewModel = fakeCounterViewModel )
    }
}

@Composable
@Preview(name = "Light Mode")
@Preview(name = "Dark Mode", showBackground = true,  uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "FontScale", fontScale = 2f)
@Preview(name = "System", showSystemUi = true)
private fun PreviewErroring(){
    class FakeHomeViewModel():HomeViewModel{

        override val currentState: State<HomeState> = mutableStateOf(HomeState.Erroring)
        override val uiLoadingState: State<HomeStateWhenLoading> = mutableStateOf(
            HomeStateWhenLoading(
                message = "Uh-oh. Something went wrong..."
            )
        )
        override val uiDisplayState: State<HomeStateWhenDisplaying> = mutableStateOf(HomeStateWhenDisplaying())
        override val uiErroringState: State<HomeStateWhenErroring> = mutableStateOf(HomeStateWhenErroring())
        override fun onEvent(event: CounterEvent) {}
    }
    val fakeCounterViewModel = FakeHomeViewModel()

    Surface() {
        HomeScreen(counterViewModel = fakeCounterViewModel )
    }
}

@Composable
@Preview(name = "Light Mode")
@Preview(name = "Dark Mode", showBackground = true,  uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "FontScale", fontScale = 2f)
@Preview(name = "System", showSystemUi = true)
private fun PreviewDisplaying(){
    class FakeHomeViewModel():HomeViewModel{
        override val currentState: State<HomeState> = mutableStateOf(HomeState.Displaying)
        override val uiLoadingState: State<HomeStateWhenLoading> = mutableStateOf(HomeStateWhenLoading())
        override val uiDisplayState: State<HomeStateWhenDisplaying> = mutableStateOf(
            HomeStateWhenDisplaying(
                title = "Your Collection",
                bookCountLabel = "Book collection count",
                bookCount = 88,
                recordCountLabel = "Record collection count",
                recordCount = 132,
                buttonLabel = "Press Me Colours",
                backgroundColor = Color.LightGray
            )
        )
        override val uiErroringState: State<HomeStateWhenErroring> = mutableStateOf(HomeStateWhenErroring())
        override fun onEvent(event: CounterEvent) {}
    }
    val fakeCounterViewModel = FakeHomeViewModel()

    Surface() {
        HomeScreen(counterViewModel = fakeCounterViewModel )
    }
}