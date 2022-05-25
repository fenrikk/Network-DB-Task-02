package com.nikfen.network_db_task_02.model.local

import com.nikfen.network_db_task_02.model.local.tables.User
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class UserLocalRepository : UserLocal {
    override fun getUsers(limit: Int): Single<List<User>> {
        return LocalInstance.getDao().getUsers(limit)
    }

    override fun saveUsers(users: List<User>) {
        LocalInstance.getDao().insertUsers(users).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getUserById(id: String): Single<User> {
        return LocalInstance.getDao().getUser(id)
    }
}