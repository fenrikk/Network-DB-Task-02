package com.nikfen.network_db_task_02.model.remote

import com.nikfen.network_db_task_02.model.remote.response.UserResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {
    @GET("./api/")
    fun getUsers(@Query("results") limit: Int): Single<UserResponse>
}