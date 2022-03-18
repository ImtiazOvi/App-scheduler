package com.meimtiaz.domain.base

import androidx.lifecycle.LiveData
import com.meimtiaz.entity.resource.ApiResponse

interface BaseUseCase
interface ApiUseCase<Params, Type> : BaseUseCase {
    fun execute(params: Params? = null): LiveData<ApiResponse<Type>>
}