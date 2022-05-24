package com.nikfen.network_db_task_02.model.local

import com.nikfen.network_db_task_02.model.local.tables.User

interface UserLocal {
    fun getUsers(limit: Int): List<User>
    fun saveUsers(users: List<User>)
    fun getUserById(id: String): User
}