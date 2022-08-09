package com.jordansilva.vana.ui.components

import android.os.Parcelable
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jordansilva.vana.ui.theme.LightGrey
import com.jordansilva.vana.ui.theme.VanaTheme
import com.jordansilva.vana.ui.theme.Yellow80
import kotlinx.parcelize.Parcelize
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


@Composable
fun Calendar(visibleItems: Int = 10) {
    val days = initializeList(visibleItems)

    val state = rememberLazyListState()
    var selected by rememberSaveable { mutableStateOf(days.first()) }

    Column(
        modifier = Modifier.padding(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        CalendarHeader()

        LazyRow(
            state = state,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item { Spacer(modifier = Modifier.width(4.dp)) }
            items(days) {
                CalendarDay(
                    dayOfWeek = it.dayOfWeekShort,
                    dayOfMonth = it.day,
                    selected = selected == it
                ) { selected = it }
            }
        }
    }
}

@Composable
private fun CalendarHeader() {
    val dateFormat = DateFormat.getDateInstance()
    val date = dateFormat.format(Calendar.getInstance(Locale.getDefault()).time)
    Text(date, modifier = Modifier.padding(start = 12.dp), fontWeight = FontWeight.SemiBold, fontSize = 12.sp)
}

@Composable
fun CalendarDay(dayOfWeek: String, dayOfMonth: Int, selected: Boolean, onClick: () -> Unit) {
    val backgroundColor = if (selected) Yellow80 else Color.Transparent
    val borderColor = if (selected) Yellow80 else LightGrey
    val size = if (selected) 90.dp else 42.dp

    val modifier = Modifier
        .clip(RoundedCornerShape(14.dp))
        .width(size)
        .background(backgroundColor)
        .border(1.dp, borderColor, RoundedCornerShape(14.dp))
        .padding(8.dp)

    if (selected) {
        Row(modifier, verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Outlined.ThumbUp,
                contentDescription = "",
                modifier = Modifier
                    .weight(1f)
                    .size(36.dp)
            )
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(dayOfWeek, fontWeight = FontWeight.SemiBold, fontSize = 12.sp, color = LightGrey)
                Text(dayOfMonth.toString(), fontWeight = FontWeight.ExtraBold, fontSize = 12.sp, color = DarkGray)
            }
        }
    } else {
        Column(modifier, horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(dayOfWeek, fontWeight = FontWeight.SemiBold, fontSize = 12.sp, color = LightGrey)
            Text(dayOfMonth.toString(), fontWeight = FontWeight.ExtraBold, fontSize = 12.sp, color = LightGrey)
        }
    }
}

@Parcelize
private data class CalendarItem(
    val day: Int,
    val month: Int,
    val year: Int,
    val dayOfWeek: Int,
    val dayOfWeekShort: String,
) : Parcelable


@Composable
private fun initializeList(visibleItems: Int): List<CalendarItem> {
    val dayFormat = SimpleDateFormat("EEE", Locale.getDefault())
    val calendar = Calendar.getInstance(Locale.getDefault())

    CalendarItem(
        day = calendar.get(Calendar.DAY_OF_MONTH),
        month = calendar.get(Calendar.MONTH),
        year = calendar.get(Calendar.YEAR),
        dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK),
        dayOfWeekShort = dayFormat.format(calendar.time)
    )

    return (1..visibleItems).map {
        calendar.add(Calendar.DAY_OF_MONTH, 1)

        CalendarItem(
            day = calendar.get(Calendar.DAY_OF_MONTH),
            month = calendar.get(Calendar.MONTH),
            year = calendar.get(Calendar.YEAR),
            dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK),
            dayOfWeekShort = dayFormat.format(calendar.time)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CalendarPreview() {
    VanaTheme {
        Calendar()
    }
}