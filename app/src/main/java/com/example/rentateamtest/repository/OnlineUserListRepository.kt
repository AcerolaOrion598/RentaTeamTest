package com.example.rentateamtest.repository

import com.example.rentateamtest.model.RetrofitComponent
import com.example.rentateamtest.model.User

class OnlineUserListRepository(private val retrofitComponent: RetrofitComponent) : IUserListRepository {

    override fun getUserList(): ArrayList<User>? {
        val networkInterface = retrofitComponent.getNetworkInterface()
        val response = networkInterface.getUserListResponse().execute()
        return if (response.isSuccessful)
            response.body()?.userList
        else null
    }
}