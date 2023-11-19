package com.example.ipcalculator.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun IPAndMaskInfoCard(
    @StringRes ipAddressText: Int,
    ipAddressValue: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.padding(4.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                text = ipAddressValue,
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.weight(4f)
            )
            Text(
                text = stringResource(ipAddressText),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun IpAndMaksInfoCardPreview() {
    IPAndMaskInfoCard(
        ipAddressText = 0,
        ipAddressValue = "00000000"
    )
}