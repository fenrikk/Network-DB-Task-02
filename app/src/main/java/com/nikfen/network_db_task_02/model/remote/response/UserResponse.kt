package com.nikfen.network_db_task_02.model.remote.response

data class UserResponse(val results: List<UserData>)

data class UserData(
    val gender: String,
    val name: Name,
    val email: String,
    val dob: Dob,
    val phone: String,
    val picture: Picture
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