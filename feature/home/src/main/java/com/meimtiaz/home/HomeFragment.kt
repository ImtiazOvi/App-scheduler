package com.meimtiaz.home

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.meimtiaz.common.base.BaseFragment
import com.meimtiaz.common.extfun.clickWithDebounce
import com.meimtiaz.common.extfun.observe
import com.meimtiaz.common.extfun.setUpVerticalRecyclerView
import com.meimtiaz.common.extfun.showAlertDialog
import com.meimtiaz.home.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment: BaseFragment<FragmentHomeBinding>() {
    override fun viewBindingLayout(): FragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater)

    private val viewModel by viewModels<HomeViewModel>()
    private lateinit var adapter: SchedulesAdapter

    override fun initializeView(savedInstanceState: Bundle?) {
        /** @toolBarInc common toolbar title text changed **/
        binding.toolBarInc.toolbarTitleTv.text = getString(com.meimtiaz.assets.R.string.title_schedule)

        /** @toolBarInc common toolbar back button visibility gone **/
        binding.toolBarInc.toolbarBackIV.isVisible = false

        /** @ClickWithDebounce prevent double click at the same time
         *  after click navigate to add schedule screen **/
        binding.addScheduleCv.clickWithDebounce {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToAddScheduleFragment())
        }

        adapter = SchedulesAdapter(
            application = requireContext(),
             scheduleItemCancelCallBack = { appScheduleEntity ->
                 showCancelScheduleDialog(appScheduleEntity.id)
             },
             scheduleItemEditCallBack  = { appScheduleEntity ->
                 findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToEditScheduleFragment(appScheduleEntity))
             }
        )
        requireActivity().setUpVerticalRecyclerView(binding.scheduleRv, adapter)
    }

    override fun onResume() {
        super.onResume()
        getAllSchedule()
    }

    private fun getAllSchedule(){
        observe(viewModel.getAllAppSchedule()){
            adapter.submitList(it)
            binding.noScheduleTv.isVisible = it.isEmpty()
        }
    }

    private fun showCancelScheduleDialog(scheduleDatabaseId:Int) {
        requireActivity().showAlertDialog(
            getString(
                com.meimtiaz.assets.R.string.message_yes
            ),
            getString(
                com.meimtiaz.assets.R.string.message_no
            ),
            null, getString(
                com.meimtiaz.assets.R.string.message_do_want_to_cancel_schedule
            ), true, {
                viewModel.deleteAppScheduleById(scheduleDatabaseId)
                showMessage(getString(com.meimtiaz.assets.R.string.message_schedule_deleted_successfully))
            }, {})
    }

}