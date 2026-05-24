package com.gd.expoplus.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gd.expoplus.R

@Composable
fun SplashScreen() {

    Column(
        modifier = Modifier.fillMaxSize().background(Color(0xFF120704)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.drawable.expo_plus_icon),
            contentDescription = "ExpoPlus Logo",
            modifier = Modifier
                .size(140.dp)
                .shadow(20.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))


        Text(
            text = "ExpoPlus",
            color = Color.White,
            fontSize = 38.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Expense Tracker",
            color = Color(0xFF63E65C),
            fontSize = 16.sp,
            letterSpacing = 1.sp
        )
    }
}