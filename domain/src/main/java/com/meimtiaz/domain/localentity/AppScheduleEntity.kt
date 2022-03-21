package com.meimtiaz.domain.localentity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "appschedules", indices = [Index(value = ["packageName","appName"], unique = true)])
data class AppScheduleEntity(
    @PrimaryKey(autoGenerate = true) val id:Int = 0,
    val appName:String? = "",
    val appIcon:String? = "",
    val packageName:String? = "",
    val startAt:String? = "",
    val selectedDate:String? = "",
    val selectedTime:String? = "",
    val appStartStatus:Boolean? = false,
    val created_at:Long = Calendar.getInstance().timeInMillis
)
