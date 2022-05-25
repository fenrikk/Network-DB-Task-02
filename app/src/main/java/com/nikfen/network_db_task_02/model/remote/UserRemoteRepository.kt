package com.nikfen.network_db_task_02.model.remote

import com.nikfen.network_db_task_02.model.local.tables.User
import com.nikfen.network_db_task_02.model.remote.response.toUser
import io.reactivex.rxjava3.core.Single

class UserRemoteRepository : UserRemote {
    override fun fetchUsers(limit: Int): Single<List<User>> {
        return RemoteInstance.getApi().getUsers(limit).map {
            it.results.map { it.toUser() }
        }
    }
}