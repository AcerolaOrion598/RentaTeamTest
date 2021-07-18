package com.example.rentateamtest.di

import com.example.rentateamtest.model.RetrofitProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MainModule {

    @Provides
    @Singleton
    fun provideRetrofitComponent(): RetrofitProvider {
        return RetrofitProvider()
    }
}