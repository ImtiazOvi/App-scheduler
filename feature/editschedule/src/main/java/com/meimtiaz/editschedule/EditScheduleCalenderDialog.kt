package com.meimtiaz.editschedule

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.meimtiaz.common.base.BaseBottomSheetDialogFragment
import com.meimtiaz.common.dateparser.DateTimeFormat
import com.meimtiaz.common.dateparser.DateTimeParser
import com.meimtiaz.common.extfun.IntentKey
import com.meimtiaz.editschedule.databinding.DialogEditScheduleCalenderBinding
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class EditScheduleCalenderDialog : BaseBottomSheetDialogFragment<DialogEditScheduleCalenderBinding>() {
    private val args by navArgs<EditScheduleCalenderDialogArgs>()

    override fun viewBindingLayout(): DialogEditScheduleCalenderBinding = DialogEditScheduleCalenderBinding.inflate(layoutInflater)

    override fun initializeView(savedInstanceState: Bundle?) {
        binding.calendarView.minDate = Calendar.getInstance().timeInMillis
        //if date is already selected then it will be show selected date
        if (args.selectedDate.isNotEmpty()) {
            SimpleDateFormat(DateTimeFormat.outputDMY, Locale.US)
                .parse(args.selectedDate)?.time?.let {
                    binding.calendarView.setDate(it, true, true)
                }
        }

        //on user select date then pass data in add schedule fragment
        binding.calendarView.setOnDateChangeListener { _, year, month, day ->
            findNavController().previousBackStackEntry?.savedStateHandle?.set(
                IntentKey.selectedDate,
                DateTimeParser.convertHumanFormatDate("$day-${month+1}-$year", DateTimeFormat.outputDMY))
            findNavController().popBackStack()
        }
    }
}