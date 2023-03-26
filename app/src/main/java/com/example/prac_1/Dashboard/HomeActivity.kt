package com.example.prac_1.Dashboard

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.example.prac_1.Leaves.NewLeave
import com.example.prac_1.R

class HomeActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val cardLeaves = findViewById<CardView>(R.id.cardLeaves)
        val cardComplaints = findViewById<CardView>(R.id.cardComplaints)
        val cardRulebook = findViewById<CardView>(R.id.cardRulebook)
        cardLeaves.setOnClickListener { startActivity(Intent(this,NewLeave::class.java)) }
        cardComplaints.setOnClickListener { startActivity(Intent(this,ComplaintsActivity::class.java)) }
        cardRulebook.setOnClickListener { rulebook(it) }
    }
    fun rulebook(view:View) {
        val url = "https://iiitu.ac.in/"
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }
}