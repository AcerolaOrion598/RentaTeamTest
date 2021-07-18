package com.example.rentateamtest.model

import com.example.rentateamtest.App
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitProvider {

    private var networkInterface: NetworkInterface? = null

    init {
        App.appComponent.inject(this)
    }

    fun getNetworkInterface(): NetworkInterface {
        if (networkInterface == null) {
            networkInterface = Retrofit
                .Builder()
                .baseUrl("https://reqres.in/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NetworkInterface::class.java)
        }
        return networkInterface!!
    }
}