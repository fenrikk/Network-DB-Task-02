package com.nikfen.network_db_task_02.model.remote

import com.nikfen.network_db_task_02.model.remote.response.UserResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface UserApi {
    @GET("./api/?results=25")
    fun getUsers(): Single<UserResponse>
}