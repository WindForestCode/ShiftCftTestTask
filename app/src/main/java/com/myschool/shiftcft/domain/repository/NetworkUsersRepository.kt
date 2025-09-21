package com.myschool.shiftcft.domain.repository

import com.myschool.shiftcft.domain.model.User

interface NetworkUsersRepository {
    suspend fun getUsers(count: Int) : List<User>
}