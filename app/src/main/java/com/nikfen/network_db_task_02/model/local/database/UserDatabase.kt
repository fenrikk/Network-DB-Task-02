package com.nikfen.network_db_task_02.model.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nikfen.network_db_task_02.model.local.dao.UserDao
import com.nikfen.network_db_task_02.model.local.tables.User
import javax.inject.Singleton

@Singleton
@Database(entities = [User::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}