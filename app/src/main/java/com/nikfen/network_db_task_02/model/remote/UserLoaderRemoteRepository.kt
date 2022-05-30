package com.nikfen.network_db_task_02.model.remote

import com.nikfen.network_db_task_02.model.UserLoader
import com.nikfen.network_db_task_02.model.local.tables.User
import com.nikfen.network_db_task_02.model.remote.response.toUser
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class UserLoaderRemoteRepository @Inject constructor(
    private val userApi: UserApi
) : UserLoader {
    override fun getUsers(page: Int, limit: Int): Single<List<User>> {
        return userApi.getUsers(limit).map {
            it.results.map { it.toUser() }
        }
    }
}