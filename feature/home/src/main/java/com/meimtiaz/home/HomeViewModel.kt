package com.meimtiaz.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meimtiaz.domain.localentity.AppScheduleEntity
import com.meimtiaz.domain.usecase.DeleteScheduleByIdUseCase
import com.meimtiaz.domain.usecase.GetAllAppScheduleUseCase
import com.meimtiaz.domain.usecase.UpdateAppStartStatusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private var getAllAppScheduleUseCase: GetAllAppScheduleUseCase,
    private var deleteScheduleByIdUseCase: DeleteScheduleByIdUseCase,
    private var updateAppStartStatusUseCase: UpdateAppStartStatusUseCase
):ViewModel() {

    fun getAllAppSchedule():  LiveData<List<AppScheduleEntity>>{
        return getAllAppScheduleUseCase.execute()
    }

    fun deleteAppScheduleById(id:Int){
        viewModelScope.launch {
            deleteScheduleByIdUseCase.execute(
                DeleteScheduleByIdUseCase.Params(id)
            )
        }
    }

    fun updateAppStartStatus(packageName:String, isAppStarted: Boolean){
        viewModelScope.launch {
            updateAppStartStatusUseCase.execute(
                UpdateAppStartStatusUseCase.Params(packageName, isAppStarted)
            )
        }
    }
}