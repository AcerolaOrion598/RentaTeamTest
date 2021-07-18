package com.example.rentateamtest.repository

import com.example.rentateamtest.App
import com.example.rentateamtest.database.AppDatabase
import com.example.rentateamtest.database.UserConverter
import com.example.rentateamtest.model.User
import javax.inject.Inject

@Suppress("ProtectedInFinal")
class OfflineUserListRepository : IUserListRepository {

    @Inject
    protected lateinit var appDatabase: AppDatabase

    init {
        App.appComponent.inject(this)
    }

    override fun getUserList(): ArrayList<User> {
        val userDao = appDatabase.userDao()
        val tableList = userDao.getUserList()
        val list = arrayListOf<User>()
        tableList.forEach {
            val user = UserConverter.fromTableToUser(it)
            list.add(user)
        }
        return list
    }
}