package com.meimtiaz.domain.repository

import androidx.lifecycle.LiveData
import com.meimtiaz.domain.localentity.AppScheduleEntity
import com.meimtiaz.domain.usecase.InsertAppScheduleUseCase
import com.meimtiaz.domain.usecase.UpdateAppScheduleUseCase

interface AppScheduleRepository {
    suspend fun insertAppSchedule(params: InsertAppScheduleUseCase.Params)
    fun getAllAppSchedule(): LiveData<List<AppScheduleEntity>>
    suspend fun deleteAppScheduleById(appScheduleId:Int)
    suspend fun updateAppSchedule(params: UpdateAppScheduleUseCase.Params)
    suspend fun updateAppStartStatusSchedule(packageName: String, isAppStarted: Boolean)
}