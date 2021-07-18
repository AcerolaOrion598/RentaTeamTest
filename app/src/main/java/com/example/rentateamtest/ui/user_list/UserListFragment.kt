package com.example.rentateamtest.ui.user_list

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rentateamtest.MainActivity
import com.example.rentateamtest.R
import com.example.rentateamtest.databinding.FragmentUserListBinding
import com.example.rentateamtest.model.User
import com.example.rentateamtest.repository.IUserListRepository
import com.example.rentateamtest.repository.UserListRepositoryFactory
import com.example.rentateamtest.view_model.UserListViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UserListFragment : Fragment() {

    private var binding: FragmentUserListBinding? = null
    private lateinit var userListViewModel: UserListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        userListViewModel = ViewModelProvider(this).get(UserListViewModel::class.java)
        binding = FragmentUserListBinding.inflate(inflater, container, false)
        return binding!!.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadUserList()
        binding?.let {
            it.tryAgain.setOnClickListener { _ ->
                it.errorContainer.visibility = View.GONE
                it.userListProgressBar.visibility = View.VISIBLE
                loadUserList()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    @SuppressLint("CheckResult")
    private fun loadUserList() {
        GlobalScope.launch(Dispatchers.IO) {
            val userListRepository: IUserListRepository
            try {
                userListRepository = UserListRepositoryFactory().build()
            } catch (e: Exception) {
                displayLoadingError()
                return@launch
            }

            userListViewModel.userListSource(userListRepository)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { displayLoadedList(it) }, { displayLoadingError() }
                )
        }
    }

    private fun displayLoadedList(userList: ArrayList<User>) {
        binding?.let {
            it.userListRecyclerView.adapter = UserListRecyclerViewAdapter(
                userList, ::navigateToCard
            )
            it.userListRecyclerView.layoutManager = LinearLayoutManager(context)
            it.userListProgressBar.visibility = View.GONE
            it.userListLayout.visibility = View.VISIBLE
        }
    }

    private fun displayLoadingError() {
        binding?.let {
            it.userListProgressBar.visibility = View.GONE
            it.errorContainer.visibility = View.VISIBLE
        }
    }

    private fun navigateToCard(user: User) {
        val mainActivity = activity as MainActivity?
        mainActivity?.let {
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