package com.myschool.shiftcft.model

import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val results: List<User>
)
