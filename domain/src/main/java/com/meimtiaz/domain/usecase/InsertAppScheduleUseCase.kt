package com.meimtiaz.domain.usecase

import com.meimtiaz.domain.base.CoroutineUseCase
import com.meimtiaz.domain.localentity.AppScheduleEntity
import com.meimtiaz.domain.repository.AppScheduleRepository
import javax.inject.Inject

class InsertAppScheduleUseCase @Inject constructor(
    private val repository: AppScheduleRepository
) : CoroutineUseCase<AppScheduleEntity, Unit> {

    data class Params(val appScheduleEntity:AppScheduleEntity)

    override suspend fun execute(params: AppScheduleEntity?){
        params?.let {
            repository.insertAppSchedule(params)
        }
    }
}