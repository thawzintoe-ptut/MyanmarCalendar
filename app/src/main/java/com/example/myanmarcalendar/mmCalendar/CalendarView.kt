package com.example.myanmarcalendar.mmCalendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myanmarcalendar.R
import com.example.myanmarcalendar.databinding.FragmentCalendarViewBinding
import mmcalendar.MyanmarDateConverter
import org.threeten.bp.LocalDate
import java.util.*

class CalendarView:DialogFragment(),View.OnClickListener {
    private lateinit var binding:FragmentCalendarViewBinding
    private var startPosition:Int = -1
    private val tmpEngDateList = mutableListOf<String>()
    private var engDateList = mutableListOf<String>()
    private var dateList = mutableListOf<CalendarVO>()
    private var startEngDate = LocalDate.now()
    private var startMMDate = MyanmarDateConverter.convert(
        startEngDate.year,
        startEngDate.monthValue+1,
        startEngDate.dayOfMonth)

    private val calendarAdapter by lazy { CalendarDayRecyclerAdapter() }
    private val today by lazy { LocalDate.now() }

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
        changeDate(today)


        binding.calendarEngTitle.text = String.format(
            getString(R.string.date_month_year_format),
            "${today.month}",
            "${today.year}"
        )
        binding.calendarPrevButton.setOnClickListener(this)
        binding.calendarNextButton.setOnClickListener(this)
    }

    private fun changeDate(date: LocalDate){
        engDateList =  resources.getStringArray(R.array.date_list).toList() as ArrayList
        for(i in 31 downTo (date.lengthOfMonth()+1)){
            engDateList.removeLast()
        }
        val calendar = LocalDate.of(date.year,date.month,1)
        val startDate = calendar.withDayOfMonth(1).dayOfWeek
        val endDate = calendar.withDayOfMonth(calendar.lengthOfMonth()).dayOfWeek
        for(weekDay in DayEnum.values()){
            if(startDate.value == weekDay.position){
                startPosition = weekDay.position
            }
        }
        for(i in 0 until startPosition){
            tmpEngDateList.add("")
        }
        engDateList = tmpEngDateList.apply { addAll(engDateList) }
        engDateList.forEach {
            if (it.isNotEmpty()) {
                val myanmarDay =
                    MyanmarDateConverter.convert(date.year, date.monthValue , it.toInt())
                if (myanmarDay.moonPhraseInt == 1 || myanmarDay.moonPhraseInt == 3) {
                    if (myanmarDay.moonPhraseInt == 1) {
                        dateList.add(
                            CalendarVO(
                                it,
                                myanmarDay.fortnightDay,
                                "",
                                "",
                                "",
                                MoonPhaseType.LA_PYAE
                            )
                        )
                    } else {
                        dateList.add(
                            CalendarVO(
                                it,
                                myanmarDay.fortnightDay,
                                "",
                                "",
                                "",
                                MoonPhaseType.LA_KWEL
                            )
                        )
                    }
                } else {
                    dateList.add(
                        CalendarVO(
                            it,
                            myanmarDay.fortnightDay,
                            "",
                            "",
                            "",
                            MoonPhaseType.NONE
                        )
                    )
                }
            } else {
                dateList.add(
                    CalendarVO(it, "", "", "", "", MoonPhaseType.NONE)
                )
            }
        }
        calendarAdapter.submitList(dateList)
    }

    override fun onClick(v: View?) {
        when(v){
            binding.calendarPrevButton -> {
                tmpEngDateList.clear()
                engDateList.clear()
                dateList.clear()
                startEngDate = startEngDate.minusMonths(1)
                changeDate(startEngDate)
                binding.calendarEngTitle.text = String.format(
                    getString(R.string.date_month_year_format),
                    "${startEngDate.month}",
                    "${startEngDate.year}"
                )
                calendarAdapter.notifyDataSetChanged()

            }
            binding.calendarNextButton -> {
                tmpEngDateList.clear()
                engDateList.clear()
                dateList.clear()
                startEngDate = startEngDate.plusMonths(1)
                changeDate(startEngDate)
                binding.calendarEngTitle.text = String.format(
                    getString(R.string.date_month_year_format),
                    "${startEngDate.month}",
                    "${startEngDate.year}"
                )
                calendarAdapter.notifyDataSetChanged()
            }
        }
    }

}