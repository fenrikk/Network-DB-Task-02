package com.nikfen.network_db_task_02.model.remote

import com.nikfen.network_db_task_02.model.local.tables.User

interface UserRemote {
    fun fetchUsers(): List<User>
}