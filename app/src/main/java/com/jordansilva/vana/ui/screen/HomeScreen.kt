package com.jordansilva.vana.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.jordansilva.vana.R
import com.jordansilva.vana.ui.components.Calendar
import com.jordansilva.vana.ui.theme.VanaTheme

@Composable
fun HomeScreen() {
    Column(Modifier.fillMaxSize()) {
        Greeting()
        Calendar()
    }
}

@Composable
private fun Greeting() {
    Text(stringResource(R.string.greetings_hi, "Jordan"),
        fontWeight = FontWeight.Bold)
}

@Preview(showBackground = true, device = Devices.PIXEL_3)
@Composable
private fun HomePreview() {
    VanaTheme {
        HomeScreen()
    }
}


