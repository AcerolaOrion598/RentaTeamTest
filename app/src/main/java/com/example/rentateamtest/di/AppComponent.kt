package com.example.rentateamtest.di

import com.example.rentateamtest.model.RetrofitProvider
import com.example.rentateamtest.repository.OfflineUserListRepository
import com.example.rentateamtest.repository.OnlineUserListRepository
import dagger.Component
import javax.inject.Singleton

@Component(modules = [MainModule::class, AppModule::class])
@Singleton
interface AppComponent {
    fun inject(retrofitProvider: RetrofitProvider)
    fun inject(onlineUserListRepository: OnlineUserListRepository)
    fun inject(offlineUserListRepository: OfflineUserListRepository)
}