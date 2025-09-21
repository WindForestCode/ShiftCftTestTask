package com.myschool.shiftcft.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface UsersApi {
    @GET("api/")
    suspend fun getUsers(@Query("results") count: Int): UserResponse

}