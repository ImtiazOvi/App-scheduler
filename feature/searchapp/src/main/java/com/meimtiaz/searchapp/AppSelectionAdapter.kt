package com.meimtiaz.searchapp

import android.content.Context
import android.content.pm.ApplicationInfo
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.meimtiaz.common.extfun.clickWithDebounce
import com.meimtiaz.searchapp.databinding.ItemAppBinding

class AppSelectionAdapter(
    private val context:Context
) : RecyclerView.Adapter<AppSelectionAdapter.LocationAdapterViewHolder>() {
    inner class LocationAdapterViewHolder(var binding: ItemAppBinding): RecyclerView.ViewHolder(
        binding.root
    ){
        init {
            binding.root.clickWithDebounce {
                if (::listener.isInitialized) listener.onLocationItemClick(packages[absoluteAdapterPosition])
            }
        }
    }

    private var packages = listOf<ApplicationInfo>()

    private lateinit var listener: OnAdapterClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationAdapterViewHolder {
        val binding = ItemAppBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LocationAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LocationAdapterViewHolder, position: Int) {
        holder.binding.appNameTv.text = packages[position].loadLabel(context.packageManager).toString()
        holder.binding.packageNameTv.text = packages[position].packageName
        val packageIcon: Drawable = context.packageManager.getApplicationIcon(packages[position])
        holder.binding.appIconIv.setImageDrawable(packageIcon)

    }

    override fun getItemCount(): Int = packages.size

    fun setInstalledAppList(packages: List<ApplicationInfo>){
        this.packages = packages
        notifyDataSetChanged()
    }


    interface OnAdapterClickListener{
        fun onLocationItemClick(packageInfo: ApplicationInfo)
    }

    fun setOnAdapterClickListener(listener: OnAdapterClickListener){
        this.listener = listener
    }
}