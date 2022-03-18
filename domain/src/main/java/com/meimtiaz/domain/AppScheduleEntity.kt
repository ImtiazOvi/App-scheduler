package com.meimtiaz.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "appschedules")
data class AppScheduleEntity(
    @PrimaryKey(autoGenerate = true) val id:Int = 0,
    val appName:String? = "",
    val appIcon:String? = "",
    val packageName:String? = "",
    val created_at:Long = Calendar.getInstance().timeInMillis
)