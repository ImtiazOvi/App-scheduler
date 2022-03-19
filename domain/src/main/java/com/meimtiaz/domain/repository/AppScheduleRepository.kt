package com.meimtiaz.domain.repository

import androidx.lifecycle.LiveData
import com.meimtiaz.domain.localentity.AppScheduleEntity
import com.meimtiaz.domain.usecase.InsertAppScheduleUseCase

interface AppScheduleRepository {
    suspend fun insertAppSchedule(params: InsertAppScheduleUseCase.Params)
    fun getAllAppSchedule(): LiveData<List<AppScheduleEntity>>
}