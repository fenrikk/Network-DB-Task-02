package com.nikfen.network_db_task_02.model.remote

import com.nikfen.network_db_task_02.model.local.tables.User
import io.reactivex.rxjava3.core.Single

interface UserRemote {
    fun fetchUsers(): Single<List<User>>
}