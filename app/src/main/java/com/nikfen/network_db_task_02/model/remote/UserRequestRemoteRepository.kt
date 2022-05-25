package com.nikfen.network_db_task_02.model.remote

import com.nikfen.network_db_task_02.model.UserRequest
import com.nikfen.network_db_task_02.model.local.tables.User
import com.nikfen.network_db_task_02.model.remote.response.toUser
import io.reactivex.rxjava3.core.Single

class UserRequestRemoteRepository(
    private val userApi: UserApi
) : UserRequest {
    override fun getUsers(limit: Int): Single<List<User>> {
        return userApi.getUsers(limit).map {
            it.results.map { it.toUser() }
        }
    }
}