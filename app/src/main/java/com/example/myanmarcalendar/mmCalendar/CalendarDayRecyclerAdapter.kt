package com.example.myanmarcalendar.mmCalendar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.myanmarcalendar.R
import com.example.myanmarcalendar.base.BaseRecyclerViewAdapter
import com.example.myanmarcalendar.base.BaseViewHolder
import com.example.myanmarcalendar.base.diffCallBackWith
import com.example.myanmarcalendar.databinding.ItemDayViewBinding

class CalendarDayRecyclerAdapter : BaseRecyclerViewAdapter<CalendarVO, CalendarDayViewHolder>(
    diffCallback = diffCallBackWith(
        areContentsTheSame = { oldItemId, newItemId ->
            oldItemId == newItemId
        },
        areItemTheSame = { oldItem, newItem ->
            oldItem == newItem
        }
    )
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarDayViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_day_view, null)
        return CalendarDayViewHolder(view)
    }

}


class CalendarDayViewHolder(
    itemView: View
) : BaseViewHolder<CalendarVO>(itemView) {
    private val binding: ItemDayViewBinding = ItemDayViewBinding.bind(itemView)
    override fun bind(item: CalendarVO) {

        binding.txtEngDay.text = item.engDayNumber
        binding.txtPublicHoliday.text = item.publicHoliday
        when (item.moonPhaseType) {
            MoonPhaseType.LA_PYAE -> setLaPyae()
            MoonPhaseType.LA_KWEL -> setLaKwel()
            MoonPhaseType.NONE -> setNormalMMDay(item.mmDayNumber)
        }
//        binding.linePublicHoliday.setBackgroundColor(
//            Color.parseColor("${item.color}")
//        )
    }

    private fun setLaPyae() {
        binding.txtMmDay.visibility = View.GONE
        binding.ivMoonPhase.visibility = View.VISIBLE
        binding.ivMoonPhase.setImageDrawable(
            ContextCompat.getDrawable(
                itemView.context,
                R.drawable.ic_la_pyae
            )
        )
    }

    private fun setLaKwel() {
        binding.txtMmDay.visibility = View.GONE
        binding.ivMoonPhase.visibility = View.VISIBLE
        binding.ivMoonPhase.setImageDrawable(
            ContextCompat.getDrawable(
                itemView.context,
                R.drawable.ic_la_kwel
            )
        )
    }

    private fun setNormalMMDay(mmDayNumber: String) {
        binding.txtMmDay.text = mmDayNumber
        binding.txtMmDay.visibility = View.VISIBLE
        binding.ivMoonPhase.visibility = View.GONE
    }
}

