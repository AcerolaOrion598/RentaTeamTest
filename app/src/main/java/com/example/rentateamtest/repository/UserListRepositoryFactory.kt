package com.example.rentateamtest.repository

import com.example.rentateamtest.utils.Utils
import java.io.IOException

class UserListRepositoryFactory {
    @Throws(InterruptedException::class, IOException::class)
    fun build(): IUserListRepository {
        return if (Utils.isServerAvailable())
            OnlineUserListRepository()
        else
            OfflineUserListRepository()
    }
}