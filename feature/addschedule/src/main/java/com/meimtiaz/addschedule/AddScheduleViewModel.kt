package com.meimtiaz.addschedule

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.meimtiaz.domain.localentity.AppScheduleEntity
import com.meimtiaz.domain.usecase.GetAllAppScheduleUseCase
import com.meimtiaz.domain.usecase.InsertAppScheduleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddScheduleViewModel @Inject constructor(
    private var insertAppScheduleUseCase: InsertAppScheduleUseCase,
    private var getAllAppScheduleUseCase: GetAllAppScheduleUseCase
    ) :
    ViewModel() {

    fun insertAddSchedule(params: InsertAppScheduleUseCase.Params) {
        CoroutineScope(Dispatchers.IO).launch {
            insertAppScheduleUseCase.execute(params)
        }
    }

    fun getAllAppSchedule():  LiveData<List<AppScheduleEntity>>{
       return getAllAppScheduleUseCase.execute()
    }
}