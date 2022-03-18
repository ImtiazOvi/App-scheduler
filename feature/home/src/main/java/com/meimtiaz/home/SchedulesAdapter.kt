package com.meimtiaz.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.meimtiaz.common.adapter.DataBoundListAdapter
import com.meimtiaz.entity.AppInfoEntity
import com.meimtiaz.home.databinding.ItemScheduleBinding

class SchedulesAdapter() : DataBoundListAdapter<AppInfoEntity, ItemScheduleBinding>(
    diffCallback = object : DiffUtil.ItemCallback<AppInfoEntity>(){
        override fun areItemsTheSame(oldItem: AppInfoEntity, newItem: AppInfoEntity): Boolean {
            return oldItem == newItem
        }
        override fun areContentsTheSame(oldItem: AppInfoEntity, newItem: AppInfoEntity): Boolean {
            return oldItem == newItem
        }

    }
) {
    override fun createBinding(parent: ViewGroup): ItemScheduleBinding = ItemScheduleBinding.inflate(LayoutInflater.from(parent.context), parent, false)

    override fun bind(binding: ItemScheduleBinding, item: AppInfoEntity, position: Int) {

    }
}