package com.nikfen.network_db_task_02.model.local

import com.nikfen.network_db_task_02.model.local.dao.UserDao
import com.nikfen.network_db_task_02.model.local.database.UserDatabase

object LocalInstance {
    private var userDao: UserDao? = null

    fun setDatabase(database: UserDatabase) {
        userDao = database.userDao()
    }

    fun getDao(): UserDao = userDao!!
}