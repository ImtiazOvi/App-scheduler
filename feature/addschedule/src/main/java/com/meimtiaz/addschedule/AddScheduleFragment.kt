package com.meimtiaz.addschedule

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import com.meimtiaz.addschedule.databinding.FragmentAddScheduleBinding
import com.meimtiaz.common.base.BaseFragment
import com.meimtiaz.common.extfun.clickWithDebounce
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddScheduleFragment:BaseFragment<FragmentAddScheduleBinding>() {

    override fun viewBindingLayout(): FragmentAddScheduleBinding = FragmentAddScheduleBinding.inflate(layoutInflater)

    override fun initializeView(savedInstanceState: Bundle?) {
        /** @toolBarInc common toolbar title text changed **/
        binding.toolBarInc.toolbarTitleTv.text = getString(com.meimtiaz.assets.R.string.title_add_schedule)

        /** @ClickWithDebounce prevent double click at the same time
         *  after click pop back stack **/
        binding.toolBarInc.toolbarBackIV.clickWithDebounce {
            findNavController().popBackStack()
        }
    }
}