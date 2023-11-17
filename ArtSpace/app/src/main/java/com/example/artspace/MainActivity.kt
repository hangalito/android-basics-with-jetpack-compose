package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    ArtWorkApp()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtWorkApp() {
    var itemNumber by remember { mutableStateOf(0) }
    val artTitle = when (itemNumber) {
        0 -> R.string.art1
        1 -> R.string.art2
        2 -> R.string.art3
        3 -> R.string.art4
        4 -> R.string.art5
        5 -> R.string.art6
        6 -> R.string.art7
        7 -> R.string.art8
        else -> R.string.art9
    }
    val artImage = when (itemNumber) {
        0 -> R.drawable.art1
        1 -> R.drawable.art2
        2 -> R.drawable.art3
        3 -> R.drawable.art4
        4 -> R.drawable.art5
        5 -> R.drawable.art6
        6 -> R.drawable.art7
        7 -> R.drawable.art8
        else -> R.drawable.art9
    }

    ArtSpaceTheme {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
                    .weight(6f)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    shape = RoundedCornerShape(12),
                    colors=CardDefaults.cardColors(containerColor = LocalContentColor.current)
                ) {
                    Image(
                        painter = painterResource(artImage),
                        contentDescription = stringResource(artTitle),
                        contentScale = ContentScale.Fit
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.BottomCenter)
                    .weight(2f), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ArtoWorkTitle(
                    title = stringResource(artTitle), artist = "Bartolomeu Hangalo", year = "2023"
                )
                ActionButtons(onPreviousClick = {
                    // If the counter it's zero stops decrementing
                    if (itemNumber == 0) itemNumber = 0
                    else itemNumber--
                }, onNextClick = {
                    // If the counter reaches the last image index, which is nine,
                    // it stops incrementing and remains with value of nine
                    if (itemNumber == 9) itemNumber = 9
                    else itemNumber++
                })
            }
        }
    }
}

@Composable
fun ActionButtons(
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(
                start = 16.dp, end = 16.dp, bottom = 8.dp, top = 24.dp
            )
            .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = onPreviousClick, modifier = Modifier.width(120.dp)
        ) {
            Text(stringResource(id = R.string.previous))
        }
        Button(
            onClick = onNextClick, modifier = Modifier.width(120.dp)
        ) {
            Text(stringResource(id = R.string.next))
        }
    }
}

@Composable
fun ArtoWorkTitle(
    title: String,
    artist: String,
    year: String,
    modifier: Modifier = Modifier
) {
    Surface(
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier
            .width(300.dp)
            .height(100.dp)
            .padding(8.dp),
        shape = RoundedCornerShape(9)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.Light,
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                textAlign = TextAlign.Center
            )
            Text(text = buildAnnotatedString {
                withStyle(
                    // Make the artist name bold
                    SpanStyle(fontWeight = FontWeight.Bold)
                ) {
                    append(artist)
                }
                append(" ($year)")
            })
        }
    }
}