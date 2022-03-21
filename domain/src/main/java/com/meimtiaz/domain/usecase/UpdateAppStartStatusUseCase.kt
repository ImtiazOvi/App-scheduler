package com.meimtiaz.domain.usecase

import com.meimtiaz.domain.base.CoroutineUseCase
import com.meimtiaz.domain.repository.AppScheduleRepository
import javax.inject.Inject

class UpdateAppStartStatusUseCase @Inject constructor(
    private val repository: AppScheduleRepository
) : CoroutineUseCase<UpdateAppStartStatusUseCase.Params, Unit> {
    data class Params(val packageName:String, val isAppStarted: Boolean)

    override suspend fun execute(params: Params?) {
        params?.let {
            repository.updateAppStartStatusSchedule(params.packageName, params.isAppStarted)
        }
    }
}