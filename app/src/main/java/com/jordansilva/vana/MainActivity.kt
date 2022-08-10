package com.jordansilva.vana

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.jordansilva.vana.ui.components.BottomBar
import com.jordansilva.vana.ui.navigation.Routes
import com.jordansilva.vana.ui.navigation.VanaNavHost
import com.jordansilva.vana.ui.theme.VanaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VanaTheme(false) {
                Surface(modifier = Modifier.fillMaxSize()) {
                    MainScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    var currDestination by remember { mutableStateOf(Routes.Home) }

    navController.addOnDestinationChangedListener { _, destination, _ ->
        currDestination = destination.route!!
    }

    Scaffold(
        modifier = Modifier,
        bottomBar = { BottomBar(currDestination) { navController.navigate(it.route) } },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        VanaNavHost(navController, modifier = Modifier.padding(paddingValues))
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    VanaTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            MainScreen()
        }
    }
}