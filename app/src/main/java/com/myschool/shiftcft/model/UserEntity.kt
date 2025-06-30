package com.myschool.shiftcft.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")

data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    val id: Long = 0,
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
                name = name.first,
                surname = name.last,
                birthday = dob.date,
                phone = phone,
                address = location.city,
                gender = gender,
            )
        }
    }

    fun toUser(): User = User(
        gender = gender,
        name = Name(
            title = "",
            first = name,
            last = surname
        ),
        location = Location(
            street = Street(
                number = 0,
                name = address
            ),
            city = address,
            state = "",
            country = "",
            postcode = Postcode(""),
        ),
        email = "",
        login = Login(
            uuid = "",
            username = "",
            password = "",
            salt = "",
            md5 = "",
            sha1 = "",
            sha256 = ""
        ),
        dob = Dob(
            date = birthday,
            age = 0
        ),
        registered = Registered(
            date = "",
            age = 0
        ),
        phone = phone,
        cell = "",
        id = Id(
            name = "",
            value = ""
        ),
        picture = Picture(
            large = "",
            medium = "",
            thumbnail = ""
        ),
        nationality = ""
    )
}

