package com.example.rentateamtest.ui.user_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rentateamtest.R
import com.example.rentateamtest.model.User

class UserListRecyclerViewAdapter(
    private val userList: ArrayList<User>, private val navigateToCard: (User) -> Unit
) : RecyclerView.Adapter<UserListRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.user_list_element, parent, false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(userList[position], navigateToCard)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: User, navigateToCard: (User) -> Unit) {
            itemView.findViewById<TextView>(R.id.user_first_name).text = user.firstName
            itemView.findViewById<TextView>(R.id.user_last_name).text = user.lastName
            val parentLayout = itemView.findViewById<RelativeLayout>(R.id.parent_layout_users)
            parentLayout?.setOnClickListener { _ ->
                run {
                    navigateToCard.invoke(user)
                }
            }
        }
    }
}
