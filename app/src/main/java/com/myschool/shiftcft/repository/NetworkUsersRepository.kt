package com.myschool.shiftcft.repository

import com.myschool.shiftcft.model.User

interface NetworkUsersRepository {
    suspend fun getUsers(count: Int) : List<User>
}