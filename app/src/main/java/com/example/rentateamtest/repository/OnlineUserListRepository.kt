package com.example.rentateamtest.repository

import com.example.rentateamtest.database.AppDatabase
import com.example.rentateamtest.database.UserConverter
import com.example.rentateamtest.model.RetrofitComponent
import com.example.rentateamtest.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class OnlineUserListRepository(
    private val retrofitComponent: RetrofitComponent,
    private val appDatabase: AppDatabase
) : IUserListRepository {

    override fun getUserList(): ArrayList<User>? {
        val networkInterface = retrofitComponent.getNetworkInterface()
        val response = networkInterface.getUserListResponse().execute()
        return if (response.isSuccessful) {
            val list = response.body()?.userList
            GlobalScope.launch(Dispatchers.IO) {
                restoreReceivedList(list)
            }
            response.body()?.userList
        } else
            null
    }

    private fun restoreReceivedList(userList: ArrayList<User>?) {
        userList?.let {
            val userDao = appDatabase.userDao()
            userDao.clearUserData()
            it.forEach { user ->
                val userTable = UserConverter.fromUserToTable(user)
                userDao.insert(userTable)
            }
        }
    }
}