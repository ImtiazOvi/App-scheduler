package com.meimtiaz.editschedule

import androidx.lifecycle.ViewModel
import com.meimtiaz.domain.usecase.UpdateAppScheduleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditScheduleViewModel @Inject constructor(private var updateAppScheduleUseCase: UpdateAppScheduleUseCase) :
    ViewModel() {

    fun updateAppSchedule(params: UpdateAppScheduleUseCase.Params) {
        CoroutineScope(Dispatchers.IO).launch {
            updateAppScheduleUseCase.execute(params)
        }
    }
}