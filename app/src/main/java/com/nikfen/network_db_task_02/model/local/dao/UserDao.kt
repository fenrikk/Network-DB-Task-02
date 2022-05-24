package com.nikfen.network_db_task_02.model.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.nikfen.network_db_task_02.model.local.tables.User
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface UserDao {
    @Query("SELECT * FROM user LIMIT :limit")
    fun getUsers(limit: Int): Single<List<User>>

    @Query("SELECT * FROM user WHERE uid LIKE :id")
    fun getUser(id: String): Single<User>

    @Query("DELETE FROM user")
    fun clearTable(): Completable

    @Insert
    fun insetUser(user: User): Completable

    @Insert
    fun insertUsers(users: List<User>): Completable

}