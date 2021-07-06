package com.example.myanmarcalendar.mmCalendar

import com.example.myanmarcalendar.mmCalendar.format.MoonPhaseType

data class CalendarVO(
    val engDayNumber:String,
    val mmDayNumber:String,
    val publicHoliday:String,
    val mmMonthName:String,
    val color:String,
    val event:String,
    val moonPhaseType: MoonPhaseType
)


