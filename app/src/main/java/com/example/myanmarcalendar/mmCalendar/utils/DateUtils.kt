package com.example.myanmarcalendar.mmCalendar.utils

import com.example.myanmarcalendar.mmCalendar.CalendarDay
import mmcalendar.MyanmarDateConverter

object DateUtils {
    fun getMonthListYear(year: Int): List<String> {
        val months = arrayListOf<String>()
        val monthList = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)
        monthList.forEach { month ->
            val engDate = CalendarDay(year, month, 1)
            val maxDate = if (year % 400 == 0 && engDate.getMonth() == 2) {
                29
            } else if (year % 100 == 0 && engDate.getMonth() == 2) {
                28
            } else if (year % 4 == 0 && engDate.getMonth() == 2) {
                29
            } else {
                if (engDate.getMonth() == 2) {
                    28
                } else {
                    engDate.getDate().month.maxLength()
                }
            }
            val endDate = CalendarDay.from(engDate.getYear(), engDate.getDate().monthValue, maxDate)
            val tmpBurmeseMonth = arrayListOf<String>()
            for (i in 1 until endDate.getDay()) {
                val mmDate = MyanmarDateConverter.convert(year, month, i)
                if (mmDate.monthName.isNotEmpty()) {
                    tmpBurmeseMonth.add(mmDate.monthName)
                }
            }
            months.add(
                tmpBurmeseMonth.distinct()
                    .joinToString(separator = "-")
            )
        }
        return months
    }
}