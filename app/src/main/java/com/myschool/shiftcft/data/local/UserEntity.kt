package com.myschool.shiftcft.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.myschool.shiftcft.domain.model.Dob
import com.myschool.shiftcft.domain.model.Id
import com.myschool.shiftcft.domain.model.Location
import com.myschool.shiftcft.domain.model.Login
import com.myschool.shiftcft.domain.model.Name
import com.myschool.shiftcft.domain.model.Picture
import com.myschool.shiftcft.domain.model.Postcode
import com.myschool.shiftcft.domain.model.Registered
import com.myschool.shiftcft.domain.model.Street
import com.myschool.shiftcft.domain.model.User

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
    @ColumnInfo("cellphone")
    val cellphone: String,
    @ColumnInfo("username")
    val username: String,
    @ColumnInfo("country")
    val country: String,
    @ColumnInfo("city")
    val city: String,
    @ColumnInfo("street_name")
    val streetName: String,
    @ColumnInfo("street_number")
    val streetNumber: Int,
    @ColumnInfo("gender")
    val gender: String,
    @ColumnInfo("picture_small")
    val pictureSmall: String,
    @ColumnInfo("picture_big")
    val pictureBig: String,
    @ColumnInfo("email")
    val email: String,
    @ColumnInfo("age")
    val age: Int,
) {
    companion object {
        fun fromUser(user: User): UserEntity = with(user) {
            UserEntity(
                name = name.first,
                surname = name.last,
                birthday = dob.date,
                phone = phone,
                username = login.username,
                city = location.city,
                country = location.country,
                gender = gender,
                pictureSmall = picture.thumbnail,
                pictureBig = picture.medium,
                email = email,
                cellphone = cell,
                age = dob.age,
                streetNumber = location.street.number,
                streetName = location.street.name,
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
                number = streetNumber,
                name = streetName,
            ),
            city = city,
            state = "",
            country = country,
            postcode = Postcode(""),
        ),
        email = email,
        login = Login(
            uuid = "",
            username = username,
            password = "",
            salt = "",
            md5 = "",
            sha1 = "",
            sha256 = ""
        ),
        dob = Dob(
            date = birthday,
            age = age,
        ),
        registered = Registered(
            date = "",
            age = 0
        ),
        phone = phone,
        cell = cellphone,
        id = Id(
            name = "",
            value = id.toString()
        ),
        picture = Picture(
            large = "",
            medium = pictureBig,
            thumbnail = pictureSmall
        ),
        nationality = ""
    )
}