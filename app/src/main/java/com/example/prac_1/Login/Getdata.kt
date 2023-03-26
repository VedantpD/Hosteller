package com.example.prac_1.Login

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import com.example.prac_1.Dashboard.HomeActivity
import com.example.prac_1.R
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Getdata : AppCompatActivity() {
    private lateinit var rollnoEditText : EditText
    private lateinit var nameEditText : EditText
    private lateinit var parentsEditText : EditText
    private lateinit var addressEditText : EditText
    private lateinit var phoneEditText : EditText
    private lateinit var roomEditText : EditText
    private lateinit var hostelEditText : EditText

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.getdata)
        rollnoEditText = findViewById(R.id.rollnoEditText)
        nameEditText = findViewById(R.id.nameEditText)
        parentsEditText = findViewById(R.id.parentEditText)
        addressEditText = findViewById(R.id.addressEditText)
        phoneEditText = findViewById(R.id.phoneEditText)
        roomEditText = findViewById(R.id.roomEditText)
        hostelEditText = findViewById(R.id.hostelEditText)
    }
    fun saveData(view:View){
        val rollno = rollnoEditText.text.toString()
        val name = nameEditText.text.toString()
        val parents = parentsEditText.text.toString()
        val address = addressEditText.text.toString()
        val phone = phoneEditText.text.toString()
        val room = roomEditText.text.toString()
        val hostel = hostelEditText.text.toString()

        FirebaseApp.initializeApp(this);

        val db = Firebase.firestore
        val customId = rollno
        val docRef = db.collection("studentDetails").document(customId)
        val user = hashMapOf(
            "Address" to address,
            "hostel" to hostel,
            "name" to name,
            "parentsPhoneNumber" to parents,
            "phoneNumber" to phone,
            "roll" to rollno,
            "roonNO" to room
        )
        docRef.set(user)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${customId}")
                startActivity(Intent(this, HomeActivity::class.java))
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }

    }
}