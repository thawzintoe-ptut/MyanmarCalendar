package com.example.myanmarcalendar.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<itemType> protected constructor(
    itemView: View,
    val recyclerViewItemClickListener: RecyclerViewItemClickListener? = null
) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: itemType)
}