package com.wmccd.common_ui.external.views

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wmccd.common_ui.external.values.Padding
import com.wmccd.common_ui.internal.preview.PreviewTheme

const val LabelAndValueTestTag = "LabelAndValue"

@Composable
fun LabelAndValue(
    labelText: String = "",
    valueText: String = "",
    modifier: Modifier = Modifier,
    labelStyle: TextStyle = MaterialTheme.typography.titleMedium,
    valueStyle: TextStyle = MaterialTheme.typography.titleMedium
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .testTag(LabelAndValueTestTag)
    ){
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = labelText,
                modifier = Modifier.testTag("$LabelAndValueTestTag label=$labelText"),
                style = labelStyle,
                textAlign = TextAlign.Start
            )
            Text(
                text = valueText,
                modifier = Modifier
                    .weight(1f)
                    .testTag("$LabelAndValueTestTag value=$valueText"),
                style = valueStyle,
                textAlign = TextAlign.End
            )
        }
    }
}

@Composable
@Preview
private fun Preview(){
    LabelAndValue(
        labelText = "The Mighty Bobbins says...",
        valueText = "Bobbins"
    )
}

@Composable
@Preview
private fun PreviewWithOptionals(){

    val modifier = Modifier
        .border(
            width = 1.dp,
            color = Color.Green,
            shape = RoundedCornerShape(5.dp)
        )
        .padding(Padding.medium)

    LabelAndValue(
        labelText = "The Mighty Bobbins says...",
        valueText = "Bobbins",
        modifier = modifier,
        labelStyle = MaterialTheme.typography.titleLarge,
        valueStyle = MaterialTheme.typography.headlineLarge
    )
}

@Composable
@Preview(name = "Light Mode")
@Preview(name = "Dark Mode", showBackground = true,  uiMode = UI_MODE_NIGHT_YES)
@Preview(name = "Font Scale", fontScale = 2f)
@Preview(name = "System", showSystemUi = true)
private fun PreviewWithSurface() {
    PreviewTheme {
        Surface() {
            LabelAndValue(
                labelText = "The Mighty Bobbins says...",
                valueText = "Bobbins"
            )
        }
    }

}


