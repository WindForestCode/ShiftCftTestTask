package com.myschool.shiftcft.presentation.viewmodel

import com.myschool.shiftcft.domain.model.User

data class UserUiState(
    val user: List<User> = emptyList()
)
