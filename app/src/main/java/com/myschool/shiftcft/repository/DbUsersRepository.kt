package com.myschool.shiftcft.repository

import com.myschool.shiftcft.model.User
import kotlinx.coroutines.flow.Flow

interface DbUsersRepository {
    fun getUsers(): Flow<List<User>>
    suspend fun saveUser(users: List<User>)
    suspend fun deleteAll()
    suspend fun getUser(id: Long): User?
    suspend fun getCount(): Long
    suspend fun isEmpty(): Boolean
}