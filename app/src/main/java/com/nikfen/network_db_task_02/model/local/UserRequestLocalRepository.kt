package com.nikfen.network_db_task_02.model.local

import com.nikfen.network_db_task_02.model.local.dao.UserDao
import com.nikfen.network_db_task_02.model.local.tables.User
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class UserRequestLocalRepository(
    private val userDao: UserDao
) : UserLocal{
    override fun getUsers(limit: Int): Single<List<User>> {
        return userDao.getUsers(limit)
    }

    override fun saveUsers(users: List<User>): Completable {
        return userDao.insertUsers(users)
    }

    override fun getUserById(id: String): Single<User> {
        return userDao.getUser(id).subscribeOn(Schedulers.io())
    }
}