package com.meimtiaz.addschedule

import android.os.Bundle
import com.meimtiaz.addschedule.databinding.FragmentAddScheduleBinding
import com.meimtiaz.common.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddScheduleFragment:BaseFragment<FragmentAddScheduleBinding>() {

    override fun viewBindingLayout(): FragmentAddScheduleBinding = FragmentAddScheduleBinding.inflate(layoutInflater)

    override fun initializeView(savedInstanceState: Bundle?) {

    }
}