package com.freelanxer.ktroomdb.extension

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

fun Long.millisToDate(): Date {
    return Calendar.getInstance().also {
        it.timeInMillis = this
    }.time
}

fun Long.millisToDateFormat(format: String): String =
    SimpleDateFormat(format, Locale.getDefault()).format(millisToDate())

fun Long.millisToDefaultDateTimeFormat(): String =
    millisToDateFormat("yyyy/MM/dd HH:mm")

fun Long.millisToDefaultDateFormat(): String =
    millisToDateFormat("yyyy/MM/dd")