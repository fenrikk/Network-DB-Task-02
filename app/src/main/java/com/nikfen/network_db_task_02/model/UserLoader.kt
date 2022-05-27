package com.nikfen.network_db_task_02.model

import com.nikfen.network_db_task_02.model.local.tables.User
import io.reactivex.rxjava3.core.Single

interface UserLoader {
    fun getUsers(page: Int, limit: Int): Single<List<User>>
}