package com.nikfen.network_db_task_02.model

import com.nikfen.network_db_task_02.model.local.LocalInstance
import com.nikfen.network_db_task_02.model.local.tables.User
import com.nikfen.network_db_task_02.model.remote.RemoteInstance
import com.nikfen.network_db_task_02.model.remote.response.toUser
import io.reactivex.rxjava3.core.Single

class UserDataSource : UserRepository {

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
        TODO("Not yer implemented")
    }

    override fun getUserById(id: String): Single<User> {
        return LocalInstance.getDao().getUser(id)
    }

    override fun fetchUsers(): Single<List<User>> {
        TODO("Not yet implemented")
    }
}