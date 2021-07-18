package com.example.rentateamtest.repository

import com.example.rentateamtest.model.RetrofitComponent
import com.example.rentateamtest.utils.Utils
import java.io.IOException
import kotlin.jvm.Throws

class UserListRepositoryFactory(private val retrofitComponent: RetrofitComponent) {

    @Throws(InterruptedException::class, IOException::class)
    fun build(): IUserListRepository {
        return if (Utils.isInternetAvailable())
            OnlineUserListRepository(retrofitComponent)
        else
            OfflineUserListRepository()
    }
}