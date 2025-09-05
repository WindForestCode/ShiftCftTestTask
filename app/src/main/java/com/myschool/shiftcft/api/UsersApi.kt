package com.myschool.shiftcft.api

import com.myschool.shiftcft.model.UserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface UsersApi {
    @GET("api/")
    suspend fun getUsers(@Query("results") count: Int): UserResponse

}