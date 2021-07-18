package com.example.rentateamtest.ui.user_list

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rentateamtest.MainActivity
import com.example.rentateamtest.R
import com.example.rentateamtest.databinding.FragmentUserListBinding
import com.example.rentateamtest.model.RetrofitComponent
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

    private var _binding: FragmentUserListBinding? = null
    private val binding get() = _binding!!
    private val retrofitComponent = RetrofitComponent()
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

        loadUserList()
        binding.tryAgain.setOnClickListener {
            binding.errorContainer.visibility = View.GONE
            binding.userListProgressBar.visibility = View.VISIBLE
            loadUserList()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @SuppressLint("CheckResult")
    private fun loadUserList() {
        GlobalScope.launch(Dispatchers.IO) {
            val userListRepository: IUserListRepository
            try {
                userListRepository = UserListRepositoryFactory(retrofitComponent).build()
            } catch (e: Exception) {
                displayLoadingError()
                return@launch
            }

            userListViewModel.userListSource(userListRepository)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        displayLoadedList(it)
                    }, {
                        displayLoadingError()
                    }
                )
        }
    }

    private fun displayLoadedList(userList: ArrayList<User>) {
        userListRecyclerView.adapter = UserListRecyclerViewAdapter(userList, ::navigateToCard)
        userListRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.userListProgressBar.visibility = View.GONE
        binding.userListLayout.visibility = View.VISIBLE
    }

    private fun displayLoadingError() {
        binding.userListProgressBar.visibility = View.GONE
        binding.errorContainer.visibility = View.VISIBLE
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