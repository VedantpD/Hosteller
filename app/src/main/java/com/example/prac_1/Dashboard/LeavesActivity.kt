package com.example.prac_1.Dashboard

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.prac_1.R
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*

class LeavesActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    var selectedDate: Calendar = Calendar.getInstance()
    var selectedTime: Calendar = Calendar.getInstance()


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leaves)

        val monthList = listOf<String>(
            "January",
            "February",
            "March",
            "April",
            "May",
            "June",
            "July",
            "August",
            "September",
            "October",
            "November",
            "December"
        )

        val dateButton1 = findViewById<Button>(R.id.date_button)
        val timeButton1 = findViewById<Button>(R.id.time_button)

        val dateButton2 = findViewById<Button>(R.id.date_button2)
        val timeButton2 = findViewById<Button>(R.id.time_button2)

        val getRoll = findViewById<EditText>(R.id.getRoll)
        val getReason = findViewById<EditText>(R.id.getReason)
        var departure = ""
        var arrival = ""

        val apply = findViewById<Button>(R.id.apply)

        dateButton1.setOnClickListener {
            val dateSetListener =
                DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                    selectedDate.set(year, month, dayOfMonth)
                    arrival =
                        arrival + " " + dayOfMonth.toString() + " " + monthList[month] + " " + year.toString() + " at "
                }

            val datePickerDialog = DatePickerDialog(
                this,
                dateSetListener,
                selectedDate.get(Calendar.YEAR),
                selectedDate.get(Calendar.MONTH),
                selectedDate.get(Calendar.DAY_OF_MONTH)
            )

            datePickerDialog.show()
        }


        timeButton1.setOnClickListener {
            val timeSetListener =
                TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                    selectedTime.set(Calendar.HOUR_OF_DAY, hourOfDay)
                    selectedTime.set(Calendar.MINUTE, minute)
                    arrival = arrival + hourOfDay.toString() + ":" + minute
                }

            val timePickerDialog = TimePickerDialog(
                this,
                timeSetListener,
                selectedTime.get(Calendar.HOUR_OF_DAY),
                selectedTime.get(Calendar.MINUTE),
                false
            )

            timePickerDialog.show()
        }

        dateButton2.setOnClickListener {
            val dateSetListener =
                DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                    selectedDate.set(year, month, dayOfMonth)
                    departure =
                        departure + " " + dayOfMonth.toString() + " " + monthList[month] + " " + year.toString() + " at "
                }

            val datePickerDialog = DatePickerDialog(
                this,
                dateSetListener,
                selectedDate.get(Calendar.YEAR),
                selectedDate.get(Calendar.MONTH),
                selectedDate.get(Calendar.DAY_OF_MONTH)
            )

            datePickerDialog.show()
        }


        timeButton2.setOnClickListener {
            val timeSetListener =
                TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                    selectedTime.set(Calendar.HOUR_OF_DAY, hourOfDay)
                    selectedTime.set(Calendar.MINUTE, minute)
                    departure = departure + hourOfDay.toString() + ":" + minute
                }

            val timePickerDialog = TimePickerDialog(
                this,
                timeSetListener,
                selectedTime.get(Calendar.HOUR_OF_DAY),
                selectedTime.get(Calendar.MINUTE),
                false
            )

            timePickerDialog.show()
        }


        apply.setOnClickListener {
            FirebaseApp.initializeApp(this)
            val db = Firebase.firestore


            val user = hashMapOf(
                "arrival" to arrival,
                "attendeeApproval" to 0,
                "departure" to departure,
                "reason" to getReason.text.toString(),
                "roll" to getRoll.text.toString(),
                "wardenApproval" to 0,
            )

            db.collection("pastLeaves")
                .add(user)
                .addOnSuccessListener { documentReference ->
                    Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                    val toast = Toast.makeText(applicationContext, "Leave application , registered !", Toast.LENGTH_SHORT)

// Show the toast message
                    toast.show()
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error adding document", e)
                }

        }
    }
}
