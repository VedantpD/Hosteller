package com.example.prac_1.Leaves

import android.annotation.SuppressLint
import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.prac_1.R
import com.example.prac_1.adapter.UserAdapter
import com.example.prac_1.model.User
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class Pending : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pending)
        val db = FirebaseFirestore.getInstance()
        val collectionRef = db.collection("pastLeaves")
        val query = collectionRef
            .whereEqualTo("roll", "21248")
            .whereEqualTo("attendeeApproval", 0)
            .whereEqualTo("wardenApproval", 0)

        val userList = mutableListOf<User>()
        val recyclerView = findViewById<RecyclerView>(R.id.rv2)
        Log.d("main","hello")
        query.get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val arrival = document.getString("arrival") ?: "hi"
                    val departure = document.getString("departure")?:"hi"
                    val reason = document.getString("reason")?:"hi"
                    Log.d("main",reason)
                    userList.add(User(arrival,departure,reason))
                }
                // Create an instance of the UserAdapter class with the fetched data
                val userAdapter = UserAdapter(userList)
                Log.d("size", userList.size.toString())
                // Set the UserAdapter to the RecyclerView
                recyclerView.adapter = userAdapter
                val layoutManager = LinearLayoutManager(this)
                recyclerView.layoutManager = layoutManager
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents: ", exception)
            }

    }
}