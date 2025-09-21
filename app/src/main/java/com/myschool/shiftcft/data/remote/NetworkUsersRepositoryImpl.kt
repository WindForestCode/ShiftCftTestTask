package com.myschool.shiftcft.data.remote

import com.myschool.shiftcft.domain.model.User
import com.myschool.shiftcft.domain.repository.NetworkUsersRepository
import javax.inject.Inject

class NetworkUsersRepositoryImpl @Inject constructor(
    private val api: UsersApi
) : NetworkUsersRepository {

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