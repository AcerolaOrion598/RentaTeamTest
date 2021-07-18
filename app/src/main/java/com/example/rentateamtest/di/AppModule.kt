package com.example.rentateamtest.di

import android.content.Context
import com.example.rentateamtest.App
import com.example.rentateamtest.database.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: App) {

    @Provides
    @Singleton
    fun provideContext(): Context = app

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase = AppDatabase.getInstance(context)
}