package com.nikfen.network_db_task_02.model

import com.nikfen.network_db_task_02.model.local.LocalInstance
import com.nikfen.network_db_task_02.model.local.UserLocalRepository
import com.nikfen.network_db_task_02.model.local.tables.User
import com.nikfen.network_db_task_02.model.remote.RemoteInstance
import com.nikfen.network_db_task_02.model.remote.UserRemoteRepository
import com.nikfen.network_db_task_02.model.remote.response.toUser
import io.reactivex.rxjava3.core.Single

class UserMainRepository(
    private val userLocalRepository: UserLocalRepository,
    private val userRemoteRepository: UserRemoteRepository
) : UserRepository {

    override fun getUsers(limit: Int): Single<List<User>> {
        return RemoteInstance.getApi().getUsers(limit)
            .map { it.results.map { it.toUser() } }
            .flatMap { users ->
                LocalInstance.getDao()
                    .insertUsers(users)
                    .andThen(Single.just(users))
            }
            .onErrorResumeNext { LocalInstance.getDao().getUsers(limit) }

    }

    override fun saveUsers(users: List<User>) {
        userLocalRepository.saveUsers(users)
    }

    override fun getUserById(id: String): Single<User> {
        return userLocalRepository.getUserById(id)
    }

    override fun fetchUsers(limit: Int): Single<List<User>> {
        return userRemoteRepository.fetchUsers(limit)
    }
}