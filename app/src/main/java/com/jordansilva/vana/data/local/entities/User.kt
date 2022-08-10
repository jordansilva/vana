package com.jordansilva.vana.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class User(
    @PrimaryKey val uid: UUID,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "avatar") val avatar: String?
)