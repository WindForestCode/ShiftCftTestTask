package com.myschool.shiftcft.repository

import com.myschool.shiftcft.model.User
import kotlinx.coroutines.flow.Flow

interface DbUsersRepository {
    fun getUsers(): Flow<List<User>>
    fun saveUser(users: List<User>)
    fun deleteAll()
    fun getUser(id: Long): User?
}