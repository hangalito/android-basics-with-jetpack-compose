package com.example.ipcalculator.ui

import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.EaseInOutQuad
import androidx.compose.animation.core.tween
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import com.example.ipcalculator.R
import com.example.ipcalculator.data.Address


@Composable
fun IPCalculatorApp() {
    val newMask: String
    val ipAddress: String
    val defaultMask: String
    var expanded by rememberSaveable { mutableStateOf(false) }
    var octet1 by rememberSaveable { mutableStateOf("0") }
    var octet2 by rememberSaveable { mutableStateOf("0") }
    var octet3 by rememberSaveable { mutableStateOf("0") }
    var octet4 by rememberSaveable { mutableStateOf("0") }
    var usedBits by rememberSaveable { mutableStateOf("0") }

    if (octet1.isBlank() && octet2.isBlank() && octet3.isBlank() && octet4.isBlank()) {
        ipAddress = "0.0.0.0"
        defaultMask = "0.0.0.0"
        newMask = "0.0.0.0"
        octet1 = "0"
        octet2 = "0"
        octet3 = "0"
        octet4 = "0"
    } else {
        ipAddress = "$octet1.$octet2.$octet3.$octet4"
        defaultMask = Address.getDefaultMask(usedBits.toInt())
        newMask = "0.0.0.0"
    }
    val address = Address(
        listOf(
            octet1.toInt(),
            octet2.toInt(),
            octet3.toInt(),
            octet4.toInt()
        )
    )
    val binaryIp = (Address.toBinary(address.first()) + "."
            + Address.toBinary(address.second()) + "."
            + Address.toBinary(address.third()) + "."
            + Address.toBinary(address.fourth())
            )


    Column {
        Card {
            Row(
                modifier = Modifier.padding(4.dp), verticalAlignment = Alignment.Bottom
            ) {
                IPAddressInput(
                    text = octet1,
                    onValueChange = {
                        if (it.isDigitsOnly()) octet1 = it.dropWhile { num -> num == '0' }
                        if (octet1.isEmpty()) octet1 = "0"
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number, imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions { },
                    modifier = Modifier.weight(1f)
                )
                Text(text = ".", style = MaterialTheme.typography.displayMedium)
                IPAddressInput(
                    text = octet2,
                    onValueChange = {
                        if (it.isDigitsOnly()) octet2 = it.dropWhile { num -> num == '0' }
                        if (octet2.isEmpty()) octet2 = "0"
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number, imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions { },
                    modifier = Modifier.weight(1f)
                )
                Text(text = ".", style = MaterialTheme.typography.displayMedium)
                IPAddressInput(
                    text = octet3,
                    onValueChange = {
                        if (it.isDigitsOnly()) octet3 = it.dropWhile { num -> num == '0' }
                        if (octet3.isEmpty()) octet3 = "0"
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number, imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions { },
                    modifier = Modifier.weight(1f)
                )
                Text(text = ".", style = MaterialTheme.typography.displayMedium)
                IPAddressInput(
                    text = octet4,
                    onValueChange = {
                        if (it.isDigitsOnly()) octet4 = it.dropWhile { num -> num == '0' }
                        if (octet4.isEmpty()) octet4 = "0"
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number, imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions { },
                    modifier = Modifier.weight(1f)
                )
                Text(text = "/", style = MaterialTheme.typography.displaySmall)
                IPAddressInput(
                    text = usedBits,
                    onValueChange = {
                        usedBits = it.dropWhile { num -> num == '0' }
                        if (usedBits.isEmpty()) usedBits = "0"
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number, imeAction = ImeAction.Done
                    ),
                    modifier = Modifier.weight(1f)
                )
            }
        }
        Card {
            IPAndMaskInfoCard(
                ipAddressText = R.string.ip_address, ipAddressValue = ipAddress
            )
            Divider()
            IPAndMaskInfoCard(
                ipAddressText = R.string.default_mask, ipAddressValue = defaultMask
            )
            Divider()
            IPAndMaskInfoCard(
                ipAddressText = R.string.new_mask, ipAddressValue = newMask
            )
        }
        ViewBinaryData(
            title = R.string.view_data_in_binary,
            isExpanded = expanded,
            onExpandClick = { expanded = !expanded },
            binaryIp = binaryIp,
            binaryDefaultMask = "",
            binaryNewMask = ""
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IPAddressInput(
    text: String,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions,
    modifier: Modifier = Modifier,
    keyboardActions: KeyboardActions = KeyboardActions { },
    shape: Shape = RoundedCornerShape(15.dp)
) {
    Card(
        modifier = modifier, shape = shape
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            TextField(
                value = text,
                onValueChange = onValueChange,
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                singleLine = true,
                textStyle = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
fun IPAndMaskInfoCard(
    @StringRes ipAddressText: Int, ipAddressValue: String, modifier: Modifier = Modifier
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
                style = MaterialTheme.typography.displaySmall,
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
    val icon = if (isExpanded) Icons.Default.KeyboardArrowUp
    else Icons.Default.KeyboardArrowDown

    Card(
        modifier = modifier
            .padding(4.dp)
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 370, delayMillis = 90, easing = EaseInOutQuad
                )
            )
    ) {
        Column {
            Row(
                modifier = Modifier.padding(4.dp), verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = title),
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.weight(5f)
                )
                Surface(
                    onClick = { onExpandClick() }, color = Color.Unspecified
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        modifier = Modifier.weight(1f),
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
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Preview
@Composable
fun IPCalculatorPreview() {
    IPCalculatorApp()
}