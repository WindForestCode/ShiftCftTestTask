package com.myschool.shiftcft.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("gender")
    val gender: String,
    @SerialName("name")
    val name: Name,
    @SerialName("location")
    val location: Location,
    @SerialName("email")
    val email: String,
    @SerialName("login")
    val login: Login,
    @SerialName("dob")
    val dob: Dob,
    @SerialName("registered")
    val registered: Registered,
    @SerialName("phone")
    val phone: String,
    @SerialName("cell")
    val cell: String,
    @SerialName("id")
    val id: Id,
    @SerialName("picture")
    val picture: Picture,
    @SerialName("nat")
    val nationality: String
)

@Serializable
data class Name(
    @SerialName("title")
    val title: String,
    @SerialName("first")
    val first: String,
    @SerialName("last")
    val last: String
)

@Serializable
data class Location(
    @SerialName("street")
    val street: Street,
    @SerialName("city")
    val city: String,
    @SerialName("state")
    val state: String,
    @SerialName("country")
    val country: String,
    @SerialName("postcode")
    val postcode: Int
)

@Serializable
data class Street(
    @SerialName("number")
    val number: Int,
    @SerialName("name")
    val name: String
)

@Serializable
data class Login(
    @SerialName("uuid")
    val uuid: String,
    @SerialName("username")
    val username: String,
    @SerialName("password")
    val password: String,
    @SerialName("salt")
    val salt: String,
    @SerialName("md5")
    val md5: String,
    @SerialName("sha1")
    val sha1: String,
    @SerialName("sha256")
    val sha256: String
)

@Serializable
data class Dob(
    @SerialName("date")
    val date: String,
    @SerialName("age")
    val age: Int
)

@Serializable
data class Registered(
    @SerialName("date")
    val date: String,
    @SerialName("age")
    val age: Int
)

@Serializable
data class Id(
    @SerialName("name")
    val name: String,
    @SerialName("value")
    val value: String
)

@Serializable
data class Picture(
    @SerialName("large")
    val large: String,
    @SerialName("medium")
    val medium: String,
    @SerialName("thumbnail")
    val thumbnail: String
)

