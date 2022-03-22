package com.meimtiaz.editschedule

import android.content.pm.ApplicationInfo
import android.os.Bundle
import androidx.work.Data
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.meimtiaz.common.base.BaseFragment
import com.meimtiaz.common.dateparser.DateTimeFormat
import com.meimtiaz.common.dateparser.DateTimeParser
import com.meimtiaz.common.extfun.IntentKey
import com.meimtiaz.common.extfun.clickWithDebounce
import com.meimtiaz.common.extfun.getTextFromTv
import com.meimtiaz.common.extfun.navigationBackStackResultLiveData
import com.meimtiaz.common.workmanager.AppStartNotifyWork
import com.meimtiaz.domain.localentity.AppScheduleEntity
import com.meimtiaz.domain.usecase.UpdateAppScheduleUseCase
import com.meimtiaz.editschedule.databinding.FragmentEditScheduleBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class EditScheduleFragment:BaseFragment<FragmentEditScheduleBinding>() {
    override fun viewBindingLayout(): FragmentEditScheduleBinding = FragmentEditScheduleBinding.inflate(layoutInflater)

    private val args by navArgs<EditScheduleFragmentArgs>()
    private val viewModel by viewModels<EditScheduleViewModel>()
    private var selectedAppName:String = ""
    private var selectedPackageName:String = ""

    override fun initializeView(savedInstanceState: Bundle?) {
        /** @toolBarInc common toolbar title text changed **/
        binding.toolBarInc.toolbarTitleTv.text = getString(com.meimtiaz.assets.R.string.title_edit_schedule)
        selectedPackageName = args.editAppScheduleIntentEntity?.packageName ?: ""


        /**
         * ...observe selected schedule date
         * ...update ui on changed  schedule date
         */
        navigationBackStackResultLiveData<String>(IntentKey.selectedDate)?.observe(viewLifecycleOwner) {
            binding.scheduleDateTv.text = it
        }

        /**
         * ...observe selected app info
         * ...update ui on changed  app info
         */
        navigationBackStackResultLiveData<ApplicationInfo>(IntentKey.selectedAppInfo)?.observe(viewLifecycleOwner) {
            binding.appSearchTv.text = it.loadLabel(requireContext().packageManager).toString()
            selectedAppName = it.loadLabel(requireContext().packageManager).toString()
            selectedPackageName = it.packageName
        }


        setArgumentsData()
        clickListeners()
    }

    private fun setArgumentsData(){
        binding.appSearchTv.text = args.editAppScheduleIntentEntity?.appName
        binding.scheduleDateTv.text = args.editAppScheduleIntentEntity?.selectedDate
        binding.scheduleTimeTv.text = args.editAppScheduleIntentEntity?.selectedTime
    }
    private fun clickListeners(){
        /** @ClickWithDebounce prevent double click at the same time
         *  after click pop back stack **/
        binding.toolBarInc.toolbarBackIV.clickWithDebounce {
            findNavController().popBackStack()
        }

        /**
         * ...choose date from date picker fragment
         * ...pass selected date if already selected
         */
        binding.scheduleDateTv.clickWithDebounce {
            findNavController().navigate(
                EditScheduleFragmentDirections.actionEditScheduleFragmentToEditScheduleCalenderDialog(binding.scheduleDateTv.text.toString()))
        }

        /**
         * ...choose time from time picker dialog
         * ...pass selected time if already selected
         */
        binding.scheduleTimeTv.clickWithDebounce {
            showTimePicker(binding.scheduleTimeTv)
        }

        /**
         * ...tap to navigate app search screen
         */
        binding.appSearchTv.clickWithDebounce {
            findNavController().navigate(EditScheduleFragmentDirections.actionEditScheduleFragmentToSearchAppFragment(binding.appSearchTv.getTextFromTv()))
        }



        /** @ClickWithDebounce prevent double click at the same time
         *  checking validations if success then inserting schedule **/
        binding.doneBtn.clickWithDebounce {
            if (binding.appSearchTv.getTextFromTv().isBlank()) {
                showMessage(getString(com.meimtiaz.assets.R.string.message_please_give_app_name))
                return@clickWithDebounce
            }

            if (binding.scheduleDateTv.getTextFromTv().isBlank()) {
                showMessage(getString(com.meimtiaz.assets.R.string.message_please_give_schedule_date))
                return@clickWithDebounce
            }

            if (binding.scheduleTimeTv.getTextFromTv().isBlank()) {
                showMessage(getString(com.meimtiaz.assets.R.string.message_please_give_time_for_schedule_date))
                return@clickWithDebounce
            }

            val currentTime = DateTimeParser.convertDateFormatToMilliSeconds(
                DateTimeFormat.outputDMYHM,
                DateTimeParser.getCurrentDeviceDateTime(DateTimeFormat.outputDMYHM)
            )
            val scheduleTime = DateTimeParser.convertDateFormatToMilliSeconds(DateTimeFormat.outputDMYHM, binding.scheduleDateTv.getTextFromTv() +" "+ binding.scheduleTimeTv.getTextFromTv())

            if (scheduleTime > currentTime ) {
                val data = Data.Builder().putString(AppStartNotifyWork.APP_SCHEDULE_PACKAGE_NAME, selectedPackageName).build()
                val delay = scheduleTime - currentTime
                appScheduleWork(delay, data)
                updateAppSchedule()
            } else {
                Snackbar.make(binding.root, getString(com.meimtiaz.assets.R.string.message_please_give_a_valid_date_time), Snackbar.LENGTH_LONG).show()
            }
        }

    }

    private fun appScheduleWork(delay: Long, data: Data) {
        val notificationWork = OneTimeWorkRequest.Builder(AppStartNotifyWork::class.java)
            .setInitialDelay(delay, TimeUnit.MILLISECONDS).setInputData(data).build()
        val instanceWorkManager = WorkManager.getInstance(requireContext())
        instanceWorkManager.enqueueUniqueWork(selectedPackageName,
            ExistingWorkPolicy.REPLACE, notificationWork)
    }

    /** this method will show time picker dialog and pick selected time
     *  @param textView required for check previous selected time and show selected time in textView. **/
    private fun showTimePicker(textView: TextView){
        val picker = MaterialTimePicker.Builder().apply {
            if (textView.text.isNotEmpty()) {
                val previousSelectedTime = DateTimeParser.convert12HourFormatTo24HourFormat(textView.text.toString()).split(":")
                this.setHour(previousSelectedTime[0].toInt())
                this.setMinute(previousSelectedTime[1].toInt())
            }else{
                val currentTime = DateTimeParser.timePicker().split(":")
                this.setHour(currentTime[0].toInt())
                this.setMinute(currentTime[1].toInt())
            }
        }.setTimeFormat(TimeFormat.CLOCK_12H)
            .setTitleText(getString(com.meimtiaz.assets.R.string.caption_select_time))
            .build()

        picker.show(childFragmentManager, "AddScheduleFragment")

        picker.addOnPositiveButtonClickListener {
            textView.text = DateTimeParser.convert24HourFormatTo12HourFormat("${picker.hour}:${picker.minute}")
        }
    }

    /** update app schedule data in local database **/
    private fun updateAppSchedule(){
        viewModel.updateAppSchedule(
            UpdateAppScheduleUseCase.Params(
                AppScheduleEntity(
                    id = args.editAppScheduleIntentEntity?.id!!,
                    appName = binding.appSearchTv.getTextFromTv(),
                    appIcon = requireContext().packageManager.getApplicationIcon(selectedPackageName).toString(),
                    packageName = selectedPackageName,
                    startAt = binding.scheduleDateTv.getTextFromTv() +" "+ binding.scheduleTimeTv.getTextFromTv(),
                    selectedDate = binding.scheduleDateTv.getTextFromTv(),
                    selectedTime = binding.scheduleTimeTv.getTextFromTv()
                )
            )
        )

        findNavController().popBackStack()
        showMessage(getString(com.meimtiaz.assets.R.string.message_successful))
    }
}