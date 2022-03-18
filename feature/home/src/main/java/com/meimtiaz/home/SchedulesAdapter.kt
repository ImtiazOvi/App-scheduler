package com.meimtiaz.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.meimtiaz.common.adapter.DataBoundListAdapter
import com.meimtiaz.home.databinding.ItemScheduleBinding

class SchedulesAdapter() : DataBoundListAdapter<String, ItemScheduleBinding>(
    diffCallback = object : DiffUtil.ItemCallback<String>(){
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

    }
) {
    override fun createBinding(parent: ViewGroup): ItemScheduleBinding = ItemScheduleBinding.inflate(LayoutInflater.from(parent.context), parent, false)

    override fun bind(binding: ItemScheduleBinding, item: String, position: Int) {

    }
}