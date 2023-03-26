package com.example.prac_1.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.prac_1.R
import com.example.prac_1.model.User

class UserAdapter(private val userList: List<User>) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val reason: TextView = itemView.findViewById(R.id.textReason)
        val arrival: TextView = itemView.findViewById(R.id.textArrival)
        val departure: TextView = itemView.findViewById(R.id.textDeparture)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentUser = userList[position]
        holder.reason.text = currentUser.reason
        holder.arrival.text = currentUser.arrival
        holder.departure.text = currentUser.departure
    }

    override fun getItemCount() = userList.size
}
