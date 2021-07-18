package com.example.rentateamtest.repository

import com.example.rentateamtest.database.AppDatabase
import com.example.rentateamtest.database.UserConverter
import com.example.rentateamtest.model.User

class OfflineUserListRepository(private val appDatabase: AppDatabase) : IUserListRepository {

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