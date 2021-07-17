package com.example.rentateamtest.ui.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rentateamtest.databinding.FragmentUsersBinding
import com.example.rentateamtest.model.User

class UsersFragment : Fragment() {

    private lateinit var usersViewModel: UsersViewModel
    private var _binding: FragmentUsersBinding? = null
    private lateinit var userListRecyclerView: RecyclerView

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        usersViewModel = ViewModelProvider(this).get(UsersViewModel::class.java)

        _binding = FragmentUsersBinding.inflate(inflater, container, false)
        userListRecyclerView = binding.userListRecyclerView
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userList = arrayListOf<User>()
        userList.add(
            User(
            1, "george.bluth@reqres.in", "George",
            "Bluth", "https://reqres.in/img/faces/1-image.jpg"
            )
        )
        userList.add(
            User(
                2, "janet.weaver@reqres.in", "Janet",
                "Weaver", "https://reqres.in/img/faces/2-image.jpg"
            )
        )

        userListRecyclerView.adapter = UserListRecyclerViewAdapter(userList)
        userListRecyclerView.layoutManager = LinearLayoutManager(context)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}