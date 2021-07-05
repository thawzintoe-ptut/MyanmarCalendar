package com.example.myanmarcalendar.mmCalendar

import org.threeten.bp.LocalDate
import java.util.*
import kotlin.collections.HashSet

data class CalendarVO(
    val engDayNumber:String,
    val mmDayNumber:String,
    val publicHoliday:String,
    val color:String,
    val event:String
)

