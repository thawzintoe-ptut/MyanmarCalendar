package com.example.myanmarcalendar.mmCalendar

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.myanmarcalendar.R
import com.example.myanmarcalendar.databinding.ItemDayViewBinding

class CalendarDayRecyclerAdapter :BaseRecyclerViewAdapter<CalendarVO,CalendarDayViewHolder>(
   diffCallback = diffCallBackWith(
       areContentsTheSame = { oldItemId,newItemId ->
           oldItemId == newItemId
       },
       areItemTheSame = { oldItem,newItem ->
           oldItem == newItem
       }
   )
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarDayViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_day_view,null)
        return CalendarDayViewHolder(view)
    }

}



class CalendarDayViewHolder(
    itemView:View
):BaseViewHolder<CalendarVO>(itemView){
    private val binding:ItemDayViewBinding = ItemDayViewBinding.bind(itemView)
    override fun bind(item: CalendarVO) {
        binding.txtMmDay.text = item.mmDayNumber
        binding.txtEngDay.text = item.engDayNumber
        binding.txtPublicHoliday.text = item.publicHoliday
//        binding.linePublicHoliday.setBackgroundColor(
//            Color.parseColor("${item.color}")
//        )
    }
}

