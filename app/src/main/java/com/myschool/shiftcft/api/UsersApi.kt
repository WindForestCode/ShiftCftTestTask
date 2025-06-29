package com.myschool.shiftcft.api

import com.myschool.shiftcft.model.User
import retrofit2.Call
import retrofit2.create
import retrofit2.http.GET

interface UsersApi {

    @GET("api/?results=10")
    fun getUsers(): Call<User>

    companion object {
        val INSTANCE: UsersApi by lazy {
            RetrofitFactory.INSTANCE.create()
        }
    }
}