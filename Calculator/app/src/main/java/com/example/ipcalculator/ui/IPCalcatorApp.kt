package com.example.ipcalculator.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ipcalculator.R
import com.example.ipcalculator.ui.theme.IPCalculatorTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IPCalculatorApp(
    appViewModel: CalculatorViewModel = viewModel(),
) {
    val appUiState by appViewModel.uiState.collectAsState()

    Scaffold(topBar = { AppBar() }) { scaffold ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(vertical = scaffold.calculateTopPadding().plus(8.dp), horizontal = 8.dp)
        ) {
            Card {
                Row(
                    modifier = Modifier.padding(4.dp), verticalAlignment = Alignment.Bottom
                ) {
                    IPAddressInput(
                        text = appViewModel.octet1,
                        onValueChange = {
                            if (it.isDigitsOnly() && it.length <= 3) {
                                appViewModel.updateOctet1(it)
                            }
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number, imeAction = ImeAction.Next
                        ),
                        modifier = Modifier.weight(1f)
                    )
                    Text(text = ".", fontSize = 5.1.em)
                    IPAddressInput(
                        text = appViewModel.octet2,
                        onValueChange = {
                            if (it.isDigitsOnly() && it.length <= 3) {
                                appViewModel.updateOctet2(it)
                            }
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number, imeAction = ImeAction.Next
                        ),
                        modifier = Modifier.weight(1f)
                    )
                    Text(text = ".", fontSize = 5.1.em)
                    IPAddressInput(
                        text = appViewModel.octet3,
                        onValueChange = {
                            if (it.isDigitsOnly() && it.length <= 3) {
                                appViewModel.updateOctet3(it)
                            }
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number, imeAction = ImeAction.Next
                        ),
                        modifier = Modifier.weight(1f)
                    )
                    Text(text = ".", fontSize = 5.1.em)
                    IPAddressInput(
                        text = appViewModel.octet4,
                        onValueChange = {
                            if (it.isDigitsOnly() && it.length <= 3) {
                                appViewModel.updateOctet4(it)
                            }
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number, imeAction = ImeAction.Next
                        ),
                        modifier = Modifier.weight(1f)
                    )
                    Text(text = "/", fontSize = 5.1.em)
                    IPAddressInput(
                        text = appViewModel.usedBits,
                        onValueChange = {
                            if (it.isDigitsOnly() && it.length <= 2) {
                                appViewModel.updateBorrowedBits(it)
                            }
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number, imeAction = ImeAction.Done
                        ),
                        modifier = Modifier.weight(1f)
                    )
                }
            }
            Card(Modifier.padding(vertical = 8.dp)) {
                IPAndMaskInfoCard(
                    ipAddressText = R.string.ip_address,
                    ipAddressValue = appUiState.ipAddress.toString()
                )
                Divider()
                IPAndMaskInfoCard(
                    ipAddressText = R.string.default_mask,
                    ipAddressValue = appUiState.defaultMask.toString()
                )
                Divider()
                IPAndMaskInfoCard(
                    ipAddressText = R.string.new_mask,
                    ipAddressValue = appUiState.newMask.toString()
                )
            }
            ViewBinaryData(
                title = R.string.view_data_in_binary,
                isExpanded = appUiState.expanded,
                onExpandClick = { appViewModel.expand() },
                binaryIp = appUiState.currentBinaryIp,
                binaryDefaultMask = appUiState.currentDefaultMaksBin,
                binaryNewMask = appUiState.currentNewMaskBin
            )
        }
    }
}

@Composable
fun AppBar(modifier: Modifier = Modifier) {
    Row(
        modifier
            .fillMaxWidth()
            .background(colorScheme.primary)
            .padding(bottom = 8.dp)
    ) {
        Text(
            text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(8.dp),
            color = colorScheme.onPrimary
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
                singleLine = true,
                textStyle = MaterialTheme.typography.bodyLarge,
                visualTransformation = {
                    TransformedText(buildAnnotatedString {
                        withStyle(SpanStyle(fontSize = 1.em)) {
                            append(
                                it
                            )
                        }
                    }, offsetMapping = OffsetMapping.Identity)
                }
            )
        }
    }
}

@Preview(showBackground = true,device="id:Galaxy Nexus")
@Composable
fun IPCalculatorPreview() {
    IPCalculatorTheme(darkTheme = true) {
        IPCalculatorApp()
    }
}