package com.example.myanmarcalendar.base

import android.view.View

interface RecyclerViewItemClickListener {

    fun onItemClick(view: View, position: Int)

    fun onItemLongClick(view: View, position: Int)
}

inline fun recyclerViewItemClickListener(
    crossinline onItemClick: ((View, Int) -> Unit) = { _, _ -> },
    crossinline onItemLongClick: ((View, Int) -> Unit) = { _, _ -> }
): RecyclerViewItemClickListener {
    return object : RecyclerViewItemClickListener {
        override fun onItemClick(view: View, position: Int) {
            onItemClick.invoke(view, position)
        }

        override fun onItemLongClick(view: View, position: Int) {
            onItemLongClick.invoke(view, position)
        }

    }
}