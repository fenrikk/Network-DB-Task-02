package com.nikfen.network_db_task_02.model

import com.nikfen.network_db_task_02.model.local.UserLocal
import com.nikfen.network_db_task_02.model.local.tables.User
import io.reactivex.rxjava3.core.Single

class UserRequestMainRepository(
    private val userLocal: UserLocal,
    private val userRequestRemote: UserRequest
) : UserRequestRepository {
    override fun saveUsers(users: List<User>) {
        userLocal.saveUsers(users)
    }

    override fun getUserById(id: String): Single<User> {
        return userLocal.getUserById(id)
    }

    override fun getUsers(limit: Int): Single<List<User>> {
        return userRequestRemote.getUsers(limit).onErrorResumeNext { userLocal.getUsers(limit) }
    }
}