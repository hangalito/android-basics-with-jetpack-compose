package com.example.ipcalculator.ui

import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.EaseInOutQuad
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ipcalculator.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewBinaryData(
    @StringRes title: Int,
    isExpanded: Boolean,
    binaryIp: String,
    binaryDefaultMask: String,
    binaryNewMask: String,
    onExpandClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val rotationState by animateFloatAsState(
        targetValue = if (isExpanded) 180f else 0f,
        label = ""
    )

    Card(
        modifier = modifier
            .padding(4.dp)
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300, easing = EaseInOutQuad
                )
            )
    ) {
        Column {
            Row(
                modifier = Modifier.padding(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = title),
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.weight(5f)
                )
                Surface(
                    onClick = { onExpandClick() },
                    color = Color.Unspecified,
                    modifier = if (isExpanded) {
                        Modifier.rotate(360f)
                    } else {
                        Modifier
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = null,
                        modifier = Modifier
                            .rotate(rotationState),
                    )
                }
            }
            if (isExpanded) {
                Column {
                    BinaryValue(
                        binaryValue = binaryIp,
                        description = R.string.ip_address,
                    )
                    BinaryValue(
                        binaryValue = binaryDefaultMask,
                        description = R.string.default_mask,
                    )
                    BinaryValue(
                        binaryValue = binaryNewMask,
                        description = R.string.new_mask,
                    )
                }
            }
        }
    }
}

@Composable
fun BinaryValue(
    binaryValue: String,
    @StringRes description: Int,
) {
    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = binaryValue,
                maxLines = 1,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .weight(5f)
                    .horizontalScroll(rememberScrollState())
            )
            Text(
                text = stringResource(description),
                textAlign = TextAlign.End,
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.labelSmall,
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ViewBinaryDataPreview() {
    ViewBinaryData(
        title = R.string.view_data_in_binary,
        isExpanded = true,
        binaryIp = "00000000.00000000.00000000.00000000",
        binaryDefaultMask = "00000000.00000000.00000000.00000000",
        binaryNewMask = "00000000.00000000.00000000.00000000",
        onExpandClick = { /*TODO*/ })
}