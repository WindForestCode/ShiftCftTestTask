package com.myschool.shiftcft.repository

import com.myschool.shiftcft.model.User
import com.myschool.shiftcft.util.Callback

interface NetworkUsersRepository {
    fun getUsers(count: Int, callback: Callback<List<User>>)
}