package com.myschool.shiftcft.data.remote

import com.myschool.shiftcft.domain.model.User
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val results: List<User>
)