package com.nikfen.network_db_task_02.model.local

import com.nikfen.network_db_task_02.model.local.tables.User
import io.reactivex.rxjava3.core.Single

interface UserLocal {
    fun getUsers(limit: Int): Single<List<User>>
    fun saveUsers(users: List<User>)
    fun getUserById(id: String): Single<User>
}