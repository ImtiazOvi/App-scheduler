package com.meimtiaz.data

import androidx.lifecycle.LiveData
import co.meimtiaz.cache.dao.AppScheduleDao
import com.meimtiaz.domain.localentity.AppScheduleEntity
import com.meimtiaz.domain.repository.AppScheduleRepository
import com.meimtiaz.domain.usecase.InsertAppScheduleUseCase
import com.meimtiaz.domain.usecase.UpdateAppScheduleUseCase
import javax.inject.Inject

class AppScheduleRepoImpl @Inject constructor(private val appScheduleDao : AppScheduleDao): AppScheduleRepository {
    override suspend fun insertAppSchedule(params: InsertAppScheduleUseCase.Params) {
        appScheduleDao.insertAppSchedule(params.appScheduleEntity)
    }

    override fun getAllAppSchedule(): LiveData<List<AppScheduleEntity>> {
        return appScheduleDao.getAllAppSchedules()
    }

    override suspend fun deleteAppScheduleById(appScheduleId: Int) {
        appScheduleDao.deleteAppScheduleById(appScheduleId)
    }

    override suspend fun updateAppSchedule(params: UpdateAppScheduleUseCase.Params) {
        appScheduleDao.updateAppSchedule(params.appScheduleEntity)
    }

    override suspend fun updateAppStartStatusSchedule(packageName: String, isAppStarted: Boolean) {
        appScheduleDao.updateAppStartStatus(packageName, isAppStarted)
    }
}