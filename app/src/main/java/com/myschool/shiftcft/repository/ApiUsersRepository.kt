package com.myschool.shiftcft.repository

import com.myschool.shiftcft.api.UsersApi
import com.myschool.shiftcft.model.User
import com.myschool.shiftcft.model.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiUsersRepository(private val api: UsersApi) : NetworkUsersRepository {

    override fun getUsers(count: Int, callback: com.myschool.shiftcft.util.Callback<List<User>>) {
        api.getUsers(count).enqueue(
            object : Callback<UserResponse> {
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    if (response.isSuccessful) {
                        val body = response.body()
                        if (body == null || body.results.isEmpty()) {
                            callback.onError(RuntimeException("Response body is null or empty"))
                            return
                        }
                        callback.onSuccess(body.results)
                    } else {
                        callback.onError(RuntimeException("Response code: ${response.code()}"))
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    callback.onError(t)
                }
            }
        )
    }
}

