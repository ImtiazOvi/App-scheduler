package com.meimtiaz.domain.usecase

import com.meimtiaz.domain.base.CoroutineUseCase
import com.meimtiaz.domain.repository.AppScheduleRepository
import javax.inject.Inject

class DeleteScheduleByIdUseCase  @Inject constructor(
    private val repository: AppScheduleRepository
) : CoroutineUseCase<DeleteScheduleByIdUseCase.Params, Unit> {

    data class Params(val appScheduleId:Int)

    override suspend fun execute(params: Params?) {
        params?.appScheduleId?.let {
            repository.deleteAppScheduleById(it)
        }
    }
}