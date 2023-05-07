package com.wmccd.whatisnext.common_ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.wmccd.common_ui.external.views.LabelAndValue
import com.wmccd.common_ui.external.views.LabelAndValueTestTag
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
class LabelAndValueTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @ExperimentalFoundationApi
    @Test
    fun create_testTags_foundAndCorrect() {

        //assemble
        val expectedLabel = "A"
        val expectedValue = "B"
        val expectedCount = 1

        // act
        composeTestRule.setContent {
            LabelAndValue(
                labelText = expectedLabel,
                valueText = expectedValue
            )
        }

        //assert
        composeTestRule
            .onAllNodesWithTag(LabelAndValueTestTag)
            .assertCountEquals(expectedCount)

        composeTestRule
            .onAllNodesWithTag("$LabelAndValueTestTag label=$expectedLabel")
            .assertCountEquals(expectedCount)

        composeTestRule
            .onAllNodesWithTag("$LabelAndValueTestTag value=$expectedValue")
            .assertCountEquals(expectedCount)
    }
}