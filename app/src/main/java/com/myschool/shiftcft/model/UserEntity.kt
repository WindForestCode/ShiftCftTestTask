package com.myschool.shiftcft.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")

data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    val id: Long,
    @ColumnInfo("name")
    val name: String,
    @ColumnInfo("surname")
    val surname: String,
    @ColumnInfo("birthday")
    val birthday: String,
    @ColumnInfo("phone")
    val phone: String,
    @ColumnInfo("address")
    val address: String,
    @ColumnInfo("gender")
    val gender: String,
) {
    companion object {
        fun fromUser(user: User): UserEntity = with(user) {
            UserEntity(
                id = id,
                name = name,
                surname = surname,
                birthday = birthday,
                phone = phone,
                address = address,
                gender = gender,
            )
        }
    }

    fun toUser(): User = User(
        id = id,
        name = name,
        surname = surname,
        birthday = birthday,
        phone = phone,
        address = address,
        gender = gender,
    )
}

