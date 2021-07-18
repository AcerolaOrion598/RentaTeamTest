package com.example.rentateamtest.repository

import com.example.rentateamtest.model.User

interface IUserListRepository {
    fun getUserList(): ArrayList<User>?
}