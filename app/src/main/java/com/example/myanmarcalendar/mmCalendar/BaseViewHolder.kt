package com.example.myanmarcalendar.mmCalendar

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.myanmarcalendar.base.RecyclerViewItemClickListener

abstract class BaseViewHolder<itemType> protected constructor(
    itemView: View,
    val recyclerViewItemClickListener: RecyclerViewItemClickListener? = null
) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: itemType)
}