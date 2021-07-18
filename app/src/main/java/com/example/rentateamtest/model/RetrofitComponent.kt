package com.example.rentateamtest.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitComponent {

    private var networkInterface: NetworkInterface? = null

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