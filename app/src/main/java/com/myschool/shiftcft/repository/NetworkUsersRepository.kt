package com.myschool.shiftcft.repository

import com.myschool.shiftcft.model.User
import com.myschool.shiftcft.util.Callback

interface NetworkUsersRepository {
    suspend fun getUsers(count: Int) : List<User>
}