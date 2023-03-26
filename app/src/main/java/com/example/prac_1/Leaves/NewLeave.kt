package com.example.prac_1.Leaves

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.prac_1.Dashboard.LeavesActivity
import com.example.prac_1.R

class NewLeave : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_leave)
        val new = findViewById<Button>(R.id.button3)
        val pending = findViewById<Button>(R.id.button5)
        val history = findViewById<Button>(R.id.button4)

        new.setOnClickListener {
            startActivity(Intent(this,LeavesActivity::class.java))
        }
        pending.setOnClickListener {
            startActivity(Intent(this,Pending::class.java))
        }
        history.setOnClickListener {
            startActivity(Intent(this,History::class.java))
        }
    }
}