package com.meimtiaz.entity

import java.io.Serializable

data class EditAppScheduleIntentEntity(
    val id: Int = 0,
    val appName: String? = "",
    val appIcon: String? = "",
    val packageName: String? = "",
    val startAt: String? = "",
):Serializable