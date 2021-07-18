package com.example.rentateamtest

import android.app.Application
import com.example.rentateamtest.di.AppComponent
import com.example.rentateamtest.di.AppModule
import com.example.rentateamtest.di.DaggerAppComponent

class App : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}