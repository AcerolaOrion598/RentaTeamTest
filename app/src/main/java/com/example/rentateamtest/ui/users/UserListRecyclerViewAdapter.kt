package com.example.rentateamtest.ui.users

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.rentateamtest.R
import com.example.rentateamtest.model.User

class UserListRecyclerViewAdapter(private val userList: ArrayList<User>)
    : RecyclerView.Adapter<UserListRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.user_list_element, parent, false
        )
        return ViewHolder(view, parent.context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class ViewHolder(itemView: View, private val context: Context) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: User) {
            itemView.findViewById<TextView>(R.id.user_first_name).text = user.firstName
            itemView.findViewById<TextView>(R.id.user_last_name).text = user.lastName
            val parentLayout = itemView.findViewById<RelativeLayout>(R.id.parent_layout_users)
            parentLayout?.setOnClickListener { _ ->
                run {
                    Toast.makeText(context, user.id.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
