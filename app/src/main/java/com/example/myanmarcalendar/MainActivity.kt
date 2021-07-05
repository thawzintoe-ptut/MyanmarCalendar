package com.example.myanmarcalendar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myanmarcalendar.databinding.ActivityMainBinding
import com.example.myanmarcalendar.mmCalendar.CalendarView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.txtClick.setOnClickListener {
            val calendarView = CalendarView()
            calendarView.show(supportFragmentManager,calendarView.tag)
        }
    }
}