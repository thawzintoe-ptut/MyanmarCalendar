package com.example.myanmarcalendar.mmCalendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myanmarcalendar.R
import com.example.myanmarcalendar.databinding.FragmentCalendarViewBinding
import com.example.myanmarcalendar.mmCalendar.format.DayEnum
import com.example.myanmarcalendar.mmCalendar.format.MonthClickType
import com.example.myanmarcalendar.mmCalendar.format.MoonPhaseType
import com.example.myanmarcalendar.mmCalendar.utils.DateUtils
import mmcalendar.MyanmarDate
import mmcalendar.MyanmarDateConverter
import org.threeten.bp.DayOfWeek
import org.threeten.bp.LocalDate
import java.util.*

class CalendarView:DialogFragment(),View.OnClickListener {
    private lateinit var binding: FragmentCalendarViewBinding
    private var startPosition: Int = -1
    private val tmpEngDateList = mutableListOf<String>()
    private var engDateList = mutableListOf<String>()
    private var dateList = mutableListOf<CalendarVO>()
    private var startEngDate = LocalDate.now()
    private val calendarAdapter by lazy { CalendarDayRecyclerAdapter() }
    private val toDate by lazy { LocalDate.now() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCalendarViewBinding.inflate(inflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL,android.R.style.Theme_Black_NoTitleBar_Fullscreen)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvCalendarView.layoutManager = GridLayoutManager(requireContext(), 7)
        binding.rvCalendarView.hasFixedSize()
        binding.rvCalendarView.adapter = calendarAdapter
        changeDate(toDate)
        setCalendarHeader(toDate)

        binding.calendarPrevButton.setOnClickListener(this)
        binding.calendarNextButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.calendarPrevButton -> {
                nextOrPreviousMonthView(MonthClickType.PREVIOUS)
            }
            binding.calendarNextButton -> {
                nextOrPreviousMonthView(MonthClickType.NEXT)
            }
        }
    }

    /**
     * Date set for English and Myanmar Day View
     */
    private fun changeDate(date: LocalDate) {
        engDateList = resources.getStringArray(R.array.date_list).toList() as ArrayList
        for (i in 31 downTo (date.lengthOfMonth() + 1)) {
            engDateList.removeLast()
        }
        val calendar = LocalDate.of(date.year, date.month, 1)
        val startDate = calendar.withDayOfMonth(1).dayOfWeek
//        val endDate = calendar.withDayOfMonth(calendar.lengthOfMonth()).dayOfWeek
        checkStartDatePosition(startDate)
        engDateList = tmpEngDateList.apply { addAll(engDateList) }
        engDateList.forEach { engDate ->
            if (engDate.isNotEmpty()) {
                val myanmarDate =
                    MyanmarDateConverter.convert(date.year, date.monthValue, engDate.toInt())
                checkMoonPhase(myanmarDate, engDate)
            } else {
                dateList.add(
                    CalendarVO(engDate, "", "", "", "", MoonPhaseType.NONE)
                )
            }
        }
        calendarAdapter.submitList(dateList)
    }


    /**
     *  next month and previous month click event in header calendar
     */
    private fun nextOrPreviousMonthView(monthClickType: MonthClickType) {
        tmpEngDateList.clear()
        engDateList.clear()
        dateList.clear()
        when (monthClickType) {
            MonthClickType.NEXT -> {
                startEngDate = startEngDate.plusMonths(1)
                changeDate(startEngDate)
            }
            MonthClickType.PREVIOUS -> {
                startEngDate = startEngDate.minusMonths(1)
                changeDate(startEngDate)
            }
        }
        setCalendarHeader(startEngDate)
        calendarAdapter.notifyDataSetChanged()
    }

    /**
     * Check Burmese day for LA_PYAE or LA_KWEL
     */
    private fun checkMoonPhase(myanmarDate: MyanmarDate, engDate: String) {
        if (myanmarDate.moonPhraseInt == 1 || myanmarDate.moonPhraseInt == 3) {
            if (myanmarDate.moonPhraseInt == 1) {
                dateList.add(
                    CalendarVO(
                        engDate,
                        myanmarDate.fortnightDay,
                        "လပြည့်",
                        "",
                        "",
                        MoonPhaseType.LA_PYAE
                    )
                )
            } else {
                dateList.add(
                    CalendarVO(
                        engDate,
                        myanmarDate.fortnightDay,
                        "လကွယ်",
                        "",
                        "",
                        MoonPhaseType.LA_KWEL
                    )
                )
            }
        } else {
            dateList.add(
                CalendarVO(
                    engDate,
                    myanmarDate.fortnightDay,
                    "",
                    "",
                    "",
                    MoonPhaseType.NONE
                )
            )
        }
    }

    /**
     * set Header for English Month and year
     * set Header for Myanmar Month and year
     */
    private fun setCalendarHeader(date: LocalDate) {
        val mmDate = MyanmarDateConverter.convert(
            date.year,
            date.monthValue,
            1
        )
        binding.calendarEngTitle.text = String.format(
            getString(R.string.date_month_year_format),
            "${date.month}",
            "${date.year}"
        )
        binding.calendarMmTitle.text =
            String.format(
                getString(R.string.date_month_year_format),
                mmDate.year,
                DateUtils.getMonthListYear(date.year)[date.monthValue - 1]
            )
    }

    /**
     * check position of start date in weekday 7 index
     */
    private fun checkStartDatePosition(startDate: DayOfWeek) {
        for (weekDay in DayEnum.values()) {
            if (startDate.value == weekDay.position) {
                startPosition = weekDay.position
            }
        }
        for (i in 0 until startPosition) {
            tmpEngDateList.add("")
        }
    }

}