package com.jordansilva.vana.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Habit(
    @PrimaryKey val uid: UUID,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "avatar") val avatar: String?
)

data class HabitType(
    @PrimaryKey val uid: UUID,
    @ColumnInfo(name = "name") val type: String,
    @ColumnInfo(name = "color") val color: Int,
    @ColumnInfo(name = "image") val image: String,
)