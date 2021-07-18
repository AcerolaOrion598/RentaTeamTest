package com.example.rentateamtest.model

import retrofit2.Call
import retrofit2.http.GET

interface NetworkInterface {

    @GET("api/users")
    fun getUserListResponse(): Call<UserListResponse>
}