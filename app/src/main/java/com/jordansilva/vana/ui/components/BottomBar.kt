package com.jordansilva.vana.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jordansilva.vana.R
import com.jordansilva.vana.ui.navigation.Routes
import com.jordansilva.vana.ui.navigation.Screen
import com.jordansilva.vana.ui.theme.VanaTheme
import com.jordansilva.vana.ui.theme.Yellow80

private val BottomNavDestinations = listOf(
    Screen(Routes.Home, R.string.nav_home, R.drawable.ic_home),
    Screen(Routes.Calendar, R.string.nav_calendar, R.drawable.ic_calendar_today),
    Screen(Routes.MyActivities, R.string.nav_activities, R.drawable.ic_stacked_bar_chart),
    Screen(Routes.Profile, R.string.nav_profile, R.drawable.ic_face_1)
)

@Composable
fun BottomBar(
    selectedDestination: String,
    navigateTo: (Screen) -> Unit,
) {
    BottomAppBar(
        modifier = Modifier,
        tonalElevation = 0.dp,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            BottomNavDestinations.forEach { item ->
                BottomBarItem(
                    iconId = item.iconId!!,
                    label = stringResource(item.titleId),
                    selected = selectedDestination == item.route,
                    onClick = { navigateTo(item) }
                )
            }
        }
    }
}

@Composable
private fun BottomBarItem(
    @DrawableRes iconId: Int,
    label: String,
    selected: Boolean = false,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    // TODO: Get colors from Theme
    val color = if (selected) Yellow80 else Color.Transparent
    val contentColor = if (selected) Color.Black else Color.Black // Color(0xFF5B2BCF)

    Row(
        Modifier
            .clip(shape = RoundedCornerShape(100.dp))
            .animateContentSize(spring(stiffness = Spring.StiffnessMediumLow))
            .selectable(
                selected = selected,
                enabled = enabled,
                role = Role.Tab,
                onClick = { onClick.invoke() }
            )
            .background(color = color)
            .padding(horizontal = 24.dp)
            .height(48.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        Icon(
            painter = painterResource(iconId),
            contentDescription = label,
            modifier = Modifier.size(20.dp),
            tint = contentColor
        )

        if (selected) {
            Text(
                text = label,
                modifier = Modifier.padding(start = 4.dp),
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = contentColor
            )
        }
    }
}


@Composable
@Preview(showBackground = true)
private fun BottomBarPreview() {
    var selectedDestination by remember { mutableStateOf(Routes.Home) }
    VanaTheme {
        BottomBar(selectedDestination, navigateTo = { selectedDestination = it.route })
    }
}