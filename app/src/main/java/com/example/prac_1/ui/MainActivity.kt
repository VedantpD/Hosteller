package com.example.prac_1.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.prac_1.Login.Login
import com.example.prac_1.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var latitude: TextView
    private lateinit var longitude: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        latitude = findViewById(R.id.textView2)
        longitude = findViewById(R.id.textView)

        val button1 = findViewById<Button>(R.id.button)
        val button2 = findViewById<Button>(R.id.button2)
        val button_float = findViewById<FloatingActionButton>(R.id.floatingActionButton)

        button_float.setOnClickListener {
            var intent = Intent(this,Login::class.java)
            startActivity(intent)
        }

        button1.setOnClickListener {
            getlocation()
        }
        button2.setOnClickListener { getlocation() }

    }

    private fun getlocation() {
        val rollnumber = findViewById<EditText>(R.id.editTextTextPersonName3)
        if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)
        !=PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_COARSE_LOCATION)
        != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),100)
            return
        }
        val location = fusedLocationProviderClient.lastLocation
        location.addOnSuccessListener {
            if(it!=null){
                val textLatitude = "Latitude: "+it.latitude.toString()
                val textLongitude = "Longitude: "+it.longitude.toString()
                latitude.text = textLatitude
                longitude.text = textLongitude
                if(it.latitude >0 && it.longitude>0){
                    val l2 = rollnumber.text.toString()
                    savedata(l2)
                }
                else{
                    Toast.makeText(this,"ERROR",Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(this,"ERROR",Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun savedata( l2: String) {
        var url : String = "https://script.google.com/macros/s/AKfycbw02ks7g55QqSG2q13ojlRD1YgKfyQJ7Iu5SwmXVBxetxwpg1zwyHG23gD0NqZ7JZT9/exec?"
        url = url + "action=update&eroll="+l2
        val stringRequest = StringRequest(Request.Method.GET ,url, { response ->
            Toast.makeText(this@MainActivity,response,Toast.LENGTH_SHORT).show()
            Log.d("main screen",response)
        },
            { Toast.makeText(this@MainActivity,"not working",Toast.LENGTH_SHORT).show()})

        val queue = Volley.newRequestQueue(this)
        queue.add(stringRequest)
    }

}