package com.example.rentateamtest.ui.user_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavOptions
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rentateamtest.MainActivity
import com.example.rentateamtest.R
import com.example.rentateamtest.databinding.FragmentUserListBinding
import com.example.rentateamtest.model.User

class UserListFragment : Fragment() {

    private var _binding: FragmentUserListBinding? = null
    private val binding get() = _binding!!
    private lateinit var userListViewModel: UserListViewModel
    private lateinit var userListRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        userListViewModel = ViewModelProvider(this).get(UserListViewModel::class.java)

        _binding = FragmentUserListBinding.inflate(inflater, container, false)
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

        userListRecyclerView.adapter = UserListRecyclerViewAdapter(userList, ::navigateToCard)
        userListRecyclerView.layoutManager = LinearLayoutManager(context)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun navigateToCard(user: User) {
        val mainActivity = activity as MainActivity
        mainActivity.let {
            val args = Bundle()
            args.putInt("id", user.id)
            args.putString("email", user.email)
            args.putString("firstName", user.firstName)
            args.putString("lastName", user.lastName)
            args.putString("avatarUrl", user.avatarUrl)

            val navController = it.getNavController()
            navController.navigate(R.id.navigation_user_card, args)
        }
    }
}