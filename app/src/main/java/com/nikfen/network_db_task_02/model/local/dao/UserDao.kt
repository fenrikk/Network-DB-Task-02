package com.nikfen.network_db_task_02.model.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.nikfen.network_db_task_02.model.local.tables.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Query("SELECT * FROM user WHERE uid LIKE :id")
    fun getUser(id: Int): User

    @Insert
    fun insertUser(user: User)
}