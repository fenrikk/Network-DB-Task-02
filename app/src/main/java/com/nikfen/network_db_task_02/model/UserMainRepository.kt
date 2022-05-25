package com.nikfen.network_db_task_02.model

import com.nikfen.network_db_task_02.model.local.UserLocal
import com.nikfen.network_db_task_02.model.local.tables.User
import com.nikfen.network_db_task_02.model.remote.UserRemote
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class UserMainRepository(
    private val userLocal: UserLocal,
    private val userRemote: UserRemote
) : UserRepository {
    override fun saveUsers(users: List<User>): Completable {
        return userLocal.saveUsers(users)
    }

    override fun getUserById(id: String): Single<User> {
        return userLocal.getUserById(id)
    }

    override fun getUsers(limit: Int): Single<List<User>> {
        return userRemote.getUsers(limit)
            .flatMap { users ->
                userLocal.saveUsers(users).andThen(Single.just(users))
            }.onErrorResumeNext { userLocal.getUsers(limit) }

    }
}