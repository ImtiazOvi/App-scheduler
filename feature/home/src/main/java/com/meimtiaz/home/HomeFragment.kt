package com.meimtiaz.home

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.meimtiaz.common.base.BaseFragment
import com.meimtiaz.common.extfun.clickWithDebounce
import com.meimtiaz.home.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment: BaseFragment<FragmentHomeBinding>() {
    override fun viewBindingLayout(): FragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater)

    override fun initializeView(savedInstanceState: Bundle?) {
        /** @toolBarInc common toolbar title text changed **/
        binding.toolBarInc.toolbarTitleTv.text = getString(com.meimtiaz.assets.R.string.title_add_schedule)

        /** @toolBarInc common toolbar back button visibility gone **/
        binding.toolBarInc.toolbarBackIV.isVisible = false

        /** @ClickWithDebounce prevent double click at the same time
         *  after click pop back stack **/
        binding.toolBarInc.toolbarBackIV.clickWithDebounce {
            findNavController().popBackStack()
        }

        /** @ClickWithDebounce prevent double click at the same time
         *  after click navigate to add schedule screen **/
        binding.addScheduleCv.clickWithDebounce {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToAddScheduleFragment())
        }
    }
}