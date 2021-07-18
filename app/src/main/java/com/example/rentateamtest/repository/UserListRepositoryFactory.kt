package com.example.rentateamtest.repository

import com.example.rentateamtest.database.AppDatabase
import com.example.rentateamtest.model.RetrofitComponent
import com.example.rentateamtest.utils.Utils
import java.io.IOException
import kotlin.jvm.Throws

class UserListRepositoryFactory(
    private val retrofitComponent: RetrofitComponent,
    private val appDatabase: AppDatabase
) {
    @Throws(InterruptedException::class, IOException::class)
    fun build(): IUserListRepository {
        return if (Utils.isInternetAvailable())
            OnlineUserListRepository(retrofitComponent, appDatabase)
        else
            OfflineUserListRepository(appDatabase)
    }
}