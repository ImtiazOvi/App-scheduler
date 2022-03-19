package com.meimtiaz.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.meimtiaz.domain.localentity.AppScheduleEntity
import com.meimtiaz.domain.usecase.GetAllAppScheduleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private var getAllAppScheduleUseCase: GetAllAppScheduleUseCase
):ViewModel() {

    fun getAllAppSchedule():  LiveData<List<AppScheduleEntity>>{
        return getAllAppScheduleUseCase.execute()
    }
}