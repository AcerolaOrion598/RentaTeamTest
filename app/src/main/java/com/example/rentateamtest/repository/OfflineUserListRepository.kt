package com.example.rentateamtest.repository

import com.example.rentateamtest.model.User

class OfflineUserListRepository : IUserListRepository {

    override fun getUserList(): ArrayList<User>? {
        val list = arrayListOf<User>()
        list.add(User(
            1, "test@gmail.com", "Tsubasa", "Hanekawa",
            "https://i1.sndcdn.com/avatars-fo4Vr0Le0ku0izIJ-uv5qbA-t500x500.jpg")
        )
        return list
    }
}