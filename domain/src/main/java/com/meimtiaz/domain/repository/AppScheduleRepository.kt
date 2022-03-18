package com.meimtiaz.domain.repository

import com.meimtiaz.domain.localentity.AppScheduleEntity

interface AppScheduleRepository {
    suspend fun insertAppSchedule(appScheduleEntity: AppScheduleEntity)
}