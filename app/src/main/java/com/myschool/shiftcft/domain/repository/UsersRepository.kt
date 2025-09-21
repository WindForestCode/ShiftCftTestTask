package com.myschool.shiftcft.domain.repository

import com.myschool.shiftcft.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UsersRepository {

    fun getUsersFromDb(): Flow<List<User>>
    suspend fun saveUser(users: List<User>)
    suspend fun deleteAll()
    suspend fun getUser(id: Long): User?
    suspend fun getCount(): Long
    suspend fun isEmpty(): Boolean

    suspend fun getUsersFromNetwork(count: Int): List<User>

}