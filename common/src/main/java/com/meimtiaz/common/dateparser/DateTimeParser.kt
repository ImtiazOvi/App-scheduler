package com.meimtiaz.common.dateparser

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateTimeParser {

    private const val SECOND_MILLIS = 1000
    private const val MINUTE_MILLIS = 60 * SECOND_MILLIS
    private const val HOUR_MILLIS = 60 * MINUTE_MILLIS
    private const val DAY_MILLIS = 24 * HOUR_MILLIS

    fun getCurrentDeviceDateTime(format:String):String{
        val c = Calendar.getInstance().time
        val df = SimpleDateFormat(format, Locale.US)
        return df.format(c)
    }

    fun convertDateFormatToMilliSeconds(dateFormat: String,dateTime:String): Long =
        SimpleDateFormat(dateFormat,Locale.US).parse(dateTime)?.time?:0L

    fun convertHumanFormatDate(givenDateTime:String?,format:String):String{
        var formatDate = ""
        var dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.US)
        try {
            givenDateTime?.let {
                val parseDate = dateFormat.parse(it)
                dateFormat = SimpleDateFormat(format, Locale.US)
                formatDate = dateFormat.format(parseDate!!)
            }
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return formatDate
    }

}
