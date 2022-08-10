package com.jordansilva.vana.ui.screen.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jordansilva.vana.R
import com.jordansilva.vana.ui.components.ShortCalendar
import com.jordansilva.vana.ui.theme.VanaTheme
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val state by viewModel.uiState.collectAsState()
    val modifier = Modifier.padding(horizontal = 16.dp)

    Column(
        Modifier
            .fillMaxSize()
            .padding(vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Greeting(state.user, modifier)
        ShortCalendar(modifier) {  }
    }
}

@Composable
private fun Greeting(user: User?, modifier: Modifier) {
    if (user == null) return

    Row(
        modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.greetings_hi, user.name),
            modifier = Modifier.weight(1f),
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp
        )

        CoilImage(
            imageModel = user.photoUrl,
            modifier = Modifier.width(40.dp),
            contentDescription = stringResource(id = R.string.profile_picture),
            previewPlaceholder = R.drawable.ic_face_1,
            contentScale = ContentScale.Fit
        )
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_3)
@Composable
private fun HomePreview() {
    val viewModel: HomeViewModel = viewModel()
    VanaTheme {
        HomeScreen(viewModel)
    }
}


