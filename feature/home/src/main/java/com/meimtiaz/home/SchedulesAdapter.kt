package com.meimtiaz.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import com.meimtiaz.common.adapter.DataBoundListAdapter
import com.meimtiaz.common.dateparser.DateTimeFormat
import com.meimtiaz.common.dateparser.DateTimeParser
import com.meimtiaz.common.extfun.clickWithDebounce
import com.meimtiaz.common.extfun.getTextFromTv
import com.meimtiaz.domain.localentity.AppScheduleEntity
import com.meimtiaz.entity.EditAppScheduleIntentEntity
import com.meimtiaz.home.databinding.ItemScheduleBinding
import timber.log.Timber

class SchedulesAdapter(
    private val application: Context,
    private val scheduleItemCancelCallBack:((appScheduleEntity: AppScheduleEntity)->Unit)?,
    private val scheduleItemEditCallBack:((appScheduleEntity: EditAppScheduleIntentEntity)->Unit)?
) : DataBoundListAdapter<AppScheduleEntity, ItemScheduleBinding>(
    diffCallback = object : DiffUtil.ItemCallback<AppScheduleEntity>(){
        override fun areItemsTheSame(oldItem: AppScheduleEntity, newItem: AppScheduleEntity): Boolean {
            return oldItem == newItem
        }
        override fun areContentsTheSame(oldItem: AppScheduleEntity, newItem: AppScheduleEntity): Boolean {
            return oldItem == newItem
        }

    }
) {
    override fun createBinding(parent: ViewGroup): ItemScheduleBinding = ItemScheduleBinding.inflate(LayoutInflater.from(parent.context), parent, false)

    override fun bind(binding: ItemScheduleBinding, item: AppScheduleEntity, position: Int) {
        if (item.appName!!.isNotEmpty())
            binding.appNameValueTv.text = item.appName

        if (item.startAt!!.isNotEmpty())
            binding.startAtValueTv.text = item.startAt


        val currentTime = DateTimeParser.convertDateFormatToMilliSeconds(
            DateTimeFormat.outputDMYHM,
            DateTimeParser.getCurrentDeviceDateTime(DateTimeFormat.outputDMYHM)
        )
        val scheduleTime = DateTimeParser.convertDateFormatToMilliSeconds(DateTimeFormat.outputDMYHM, item.selectedDate!! +" "+item.selectedTime!!)


        if (item.isAppStarted!!) {
            binding.startStatusValueTv.text = application.getString(com.meimtiaz.assets.R.string.status_success)
            binding.cancelTv.isVisible = false
            binding.editTv.isVisible = false
        } else {
            if (scheduleTime < currentTime && !item.isAppStarted!!){
                binding.cancelTv.isVisible = true
                binding.editTv.isVisible = false
            }else{
                binding.cancelTv.isVisible = true
                binding.editTv.isVisible = true
            }
            binding.startStatusValueTv.text = application.getString(com.meimtiaz.assets.R.string.status_pending )

        }

        binding.cancelTv.clickWithDebounce {
            scheduleItemCancelCallBack?.invoke(item)
        }

        binding.editTv.clickWithDebounce {
            scheduleItemEditCallBack?.invoke(EditAppScheduleIntentEntity(
                id = item.id,
                appName = item.appName,
                appIcon = item.appIcon,
                packageName = item.packageName,
                selectedDate  = item.selectedDate,
                selectedTime  = item.selectedTime
            ))
        }
    }

}