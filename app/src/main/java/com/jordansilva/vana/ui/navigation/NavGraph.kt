package com.jordansilva.vana.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jordansilva.vana.ui.screen.HomeScreen

@Composable
fun VanaNavHost(
    navControler: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(navController = navControler, startDestination = Routes.Home, modifier = modifier) {
        composable(Routes.Home) { HomeScreen() }
        composable(Routes.Calendar) {  }
        composable(Routes.MyActivities) {  }
        composable(Routes.Profile) {  }
    }
}

object Routes {
    const val Home = "Home"
    const val Calendar = "Calendar"
    const val MyActivities = "MyActivities"
    const val Profile = "Profile"
}

data class Screen(val route: String, @StringRes val titleId: Int, @DrawableRes val iconId: Int?)
