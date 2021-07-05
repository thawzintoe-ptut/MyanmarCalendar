package com.example.myanmarcalendar.mmCalendar

import org.threeten.bp.LocalDate
import java.util.*
import kotlin.collections.HashSet

data class CalendarVO(
    val engDayNumber:String,
    val mmDayNumber:String,
    val publicHoliday:String,
    val color:String,
    val event:String,
    val moonPhaseType: MoonPhaseType
)

enum class MoonPhaseType{
    LA_PYAE,
    LA_KWEL,
    NONE
}

