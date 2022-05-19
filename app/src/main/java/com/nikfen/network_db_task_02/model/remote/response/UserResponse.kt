package com.nikfen.network_db_task_02.model.remote.response

import com.nikfen.network_db_task_02.model.local.tables.User

data class UserResponse(val results: List<UserData>)

data class UserData(
    val gender: String,
    val name: Name,
    val email: String,
    val login: Login,
    val dob: Dob,
    val phone: String,
    val picture: Picture
)

fun UserData.toUser(): User {
    return User(
        login.uuid,
        name.first,
        name.last,
        dob.age,
        email,
        phone,
        gender,
        picture.large
    )
}

data class Login(
    val uuid: String
)

data class Name(
    val title: String,
    val first: String,
    val last: String
)

data class Dob(
    val age: Int
)

data class Picture(
    val large: String,
    val medium: String,
    val thumbnail: String
)