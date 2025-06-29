package com.myschool.shiftcft.viewmodel

import com.myschool.shiftcft.model.User

data class UserUiState(
    val user: List<User> = emptyList()
)
