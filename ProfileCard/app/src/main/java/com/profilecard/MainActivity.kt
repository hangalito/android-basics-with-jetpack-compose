package com.profilecard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.profilecard.ui.theme.ProfileCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProfileCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BusinessCardApp()
                }
            }
        }
    }
}


@Composable
fun BusinessCardApp() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.align(Alignment.Center)
        ) {
            Image(
                painter = painterResource(R.drawable.user_photo),
                contentDescription = null,
                modifier = Modifier
                    .padding(8.dp)
                    .size(width = 200.dp, height = 200.dp)
            )
            Text(
                text = "Bartolomeu Hangalo",
                fontWeight = FontWeight.Bold,
                fontSize = 31.sp

            )
            Text (
                text = "Software Developer",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
        BottomCardContact(
            "+244 945 515 954",
            "+244 998 172 832",
            "bartolomeuhangalo1@gmail.com",
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }

}

@Composable
fun BottomCardContact(
    phoneNumber: String,
    whatsapp: String,
    emailAddress: String,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.Start,
        modifier = modifier
            .padding(16.dp)
    ) {
        Row(Modifier.padding(8.dp)) {
            Icon(
                painter = painterResource(id = R.drawable.ic_phone),
                contentDescription = "Phone number"
            )
            Text(
                text = phoneNumber,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
        Row(Modifier.padding(8.dp)) {
            Icon(
                painter = painterResource(id = R.drawable.ic_whatsapp),
                contentDescription = "WhatsApp"
            )
            Text(
                text = whatsapp,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
        Row(Modifier.padding(8.dp)) {
            Icon(
                painter = painterResource(id = R.drawable.ic_email),
                contentDescription = "Email address"
            )
            Text(
                text = emailAddress,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ProfileCardTheme {
        BusinessCardApp()
    }
}