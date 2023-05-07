package com.wmccd.whatisnext.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.wmccd.home.external.HomeScreen
import com.wmccd.whatisnext.home.fakes.FakeDisplayingViewModel
import com.wmccd.whatisnext.home.fakes.FakeErroringViewModel
import com.wmccd.whatisnext.home.fakes.FakeLoadingCounterViewModel
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class HomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @ExperimentalFoundationApi
    @Test
    fun erroring_testTags_foundAndCorrect() {

        //assemble
        val expectedTag = "HomeScreen erroring=ERRORING"
        val expectedCount = 1

        // act
        composeTestRule.setContent {
            HomeScreen(
               counterViewModel = FakeErroringViewModel()
            )
        }

        composeTestRule
            .onRoot(useUnmergedTree = true)
            .printToLog("--------------------------------")

        composeTestRule
            .onAllNodesWithTag(testTag = expectedTag, useUnmergedTree = true)
            .assertCountEquals(expectedCount)
    }

    @ExperimentalFoundationApi
    @Test
    fun loading_testTags_foundAndCorrect() {

        //assemble
        val expectedTag = "HomeScreen loading=LOADING"
        val expectedCount = 1

        // act
        composeTestRule.setContent {
            HomeScreen(
                counterViewModel = FakeLoadingCounterViewModel()
            )
        }

        composeTestRule
            .onRoot(useUnmergedTree = true)
            .printToLog("--------------------------------")

        composeTestRule
            .onAllNodesWithTag(testTag = expectedTag, useUnmergedTree = true)
            .assertCountEquals(expectedCount)
    }

    @ExperimentalFoundationApi
    @Test
    fun displaying_testTagsAndText_foundAndCorrect() {

        //assemble
        val expectedMainTag = "HomeScreen displaying"
        val expectedCount = 1
        val fakeDisplayingViewModel = FakeDisplayingViewModel()
        val fakeState = fakeDisplayingViewModel.uiDisplayState.value

        // act
        composeTestRule.setContent {
            HomeScreen(
                counterViewModel = fakeDisplayingViewModel
            )
        }

        composeTestRule
            .onRoot(useUnmergedTree = true)
            .printToLog("--------------------------------")

        composeTestRule
            .onAllNodesWithTag(testTag = expectedMainTag, useUnmergedTree = true)
            .assertCountEquals(expectedCount)

        composeTestRule
            .onAllNodesWithText(text=fakeState.title, useUnmergedTree = true)
            .assertCountEquals(expectedCount)

        composeTestRule
            .onAllNodesWithText(text=fakeState.bookCountLabel, useUnmergedTree = true)
            .assertCountEquals(expectedCount)

        composeTestRule
            .onAllNodesWithText(text=fakeState.recordCountLabel, useUnmergedTree = true)
            .assertCountEquals(expectedCount)

        composeTestRule
            .onAllNodesWithText(text=fakeState.bookCount.toString(), useUnmergedTree = true)
            .assertCountEquals(expectedCount)

        composeTestRule
            .onAllNodesWithText(text=fakeState.recordCount.toString(), useUnmergedTree = true)
            .assertCountEquals(expectedCount)

        composeTestRule
            .onAllNodesWithText(text=fakeState.buttonLabel, useUnmergedTree = true)
            .assertCountEquals(expectedCount)
    }

    @ExperimentalFoundationApi
    @Test
    fun displaying_clickButton_callbackIsInvoked() {

        //assemble
        val expectedInvoked = "button tapped"
        val fakeDisplayingViewModel = FakeDisplayingViewModel()
        val fakeState = fakeDisplayingViewModel.uiDisplayState.value

        // act
        composeTestRule.setContent {
            HomeScreen(
                counterViewModel = fakeDisplayingViewModel
            )
        }

        composeTestRule
            .onRoot(useUnmergedTree = true)
            .printToLog("--------------------------------")

        composeTestRule.onNodeWithTag("HomeScreen button").performClick()
        composeTestRule.waitForIdle()

        assertEquals(expectedInvoked, fakeDisplayingViewModel.lastInvoked  )
    }

}