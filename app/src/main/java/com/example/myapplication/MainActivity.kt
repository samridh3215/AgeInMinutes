package com.example.myapplication

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

class MainActivity : AppCompatActivity() {

    private var tvSelectedDate:TextView? = null
    private var tvAgeInMinutes:TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        @RequiresApi(Build.VERSION_CODES.N)
        fun clickDatePicker(){

            val myCalendar = Calendar.getInstance()
            val year = myCalendar.get(Calendar.YEAR)
            val month = myCalendar.get(Calendar.MONTH)
            val day = myCalendar.get(Calendar.DAY_OF_MONTH)

            val dpd = DatePickerDialog(this,
            { _, year, month, day ->
            Toast.makeText(this, "it works", Toast.LENGTH_SHORT).show()

            val selectedDate = "$day/${month+1}/$year"
            tvSelectedDate?.text = selectedDate

            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val theDate = sdf.parse(selectedDate)
            val selectedDateInMinutes = theDate.time/60000
            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
            val currentDateInMinutes = (currentDate.time) /60000
            val differenceInMinutes =  abs(selectedDateInMinutes - currentDateInMinutes)

            tvAgeInMinutes?.text = differenceInMinutes.toString()

        },
            year,
            month,
            day)
            dpd.show()
        }

        val btnDatePicker: Button = findViewById(R.id.datePickerBtn)

        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvAgeInMinutes = findViewById((R.id.tvAgeInMinutes))

        btnDatePicker.setOnClickListener{
            clickDatePicker()
        }



    }
}