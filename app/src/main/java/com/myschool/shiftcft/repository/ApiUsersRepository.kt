package com.myschool.shiftcft.repository

import com.myschool.shiftcft.api.UsersApi
import com.myschool.shiftcft.model.User

class ApiUsersRepository(private val api: UsersApi) : NetworkUsersRepository {

    override suspend fun getUsers(count: Int): List<User> {
        return try {
            val response = api.getUsers(count)
            if (response.results.isEmpty()) {
                throw RuntimeException("Response body is empty")
            }
            response.results
        } catch (e: Exception) {
            throw e
        }
    }
}

