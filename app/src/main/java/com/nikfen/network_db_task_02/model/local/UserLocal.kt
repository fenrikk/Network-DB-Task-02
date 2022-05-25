package com.nikfen.network_db_task_02.model.local

import com.nikfen.network_db_task_02.model.UserRequest
import com.nikfen.network_db_task_02.model.local.tables.User
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface UserLocal: UserRequest {
    fun saveUsers(users: List<User>): Completable
    fun getUserById(id: String): Single<User>
}