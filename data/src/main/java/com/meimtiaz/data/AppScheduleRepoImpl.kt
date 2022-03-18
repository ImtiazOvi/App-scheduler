package com.meimtiaz.data

import co.meimtiaz.cache.dao.AppScheduleDao
import com.meimtiaz.domain.localentity.AppScheduleEntity
import com.meimtiaz.domain.repository.AppScheduleRepository

class AppScheduleRepoImpl(private val appScheduleDao : AppScheduleDao): AppScheduleRepository {
    override suspend fun insertAppSchedule(appScheduleEntity: AppScheduleEntity) {
        appScheduleDao.insertAppSchedule(appScheduleEntity)
    }
}