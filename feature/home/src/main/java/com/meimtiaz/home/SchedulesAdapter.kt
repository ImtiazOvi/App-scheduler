package com.meimtiaz.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.meimtiaz.common.adapter.DataBoundListAdapter
import com.meimtiaz.common.extfun.clickWithDebounce
import com.meimtiaz.domain.localentity.AppScheduleEntity
import com.meimtiaz.entity.EditAppScheduleIntentEntity
import com.meimtiaz.home.databinding.ItemScheduleBinding

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