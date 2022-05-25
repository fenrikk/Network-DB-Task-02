package com.nikfen.network_db_task_02.model.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nikfen.network_db_task_02.model.local.dao.UserDao
import com.nikfen.network_db_task_02.model.local.tables.User
import com.nikfen.network_db_task_02.other.LOCAL_DATABASE_NAME


@Database(entities = [User::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        private var instance: UserDatabase? = null

        fun getInstance(context: Context): UserDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    UserDatabase::class.java, LOCAL_DATABASE_NAME
                ).build()
            }
            return instance!!
        }
    }
}