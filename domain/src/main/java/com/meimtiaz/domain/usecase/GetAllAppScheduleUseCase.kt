package com.meimtiaz.domain.usecase

import androidx.lifecycle.LiveData
import com.meimtiaz.domain.base.LocalLiveDataUseCase
import com.meimtiaz.domain.localentity.AppScheduleEntity
import com.meimtiaz.domain.repository.AppScheduleRepository
import javax.inject.Inject

class GetAllAppScheduleUseCase  @Inject constructor(
    private val appScheduleRepository: AppScheduleRepository
) : LocalLiveDataUseCase<Unit, LiveData<List<AppScheduleEntity>>> {

    override fun execute(params: Unit?): LiveData<List<AppScheduleEntity>> {
        return appScheduleRepository.getAllAppSchedule()
    }
}
