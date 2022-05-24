package com.nikfen.network_db_task_02.model

import com.nikfen.network_db_task_02.model.local.LocalInstance
import com.nikfen.network_db_task_02.model.local.tables.User
import com.nikfen.network_db_task_02.model.remote.RemoteInstance
import com.nikfen.network_db_task_02.model.remote.response.toUser
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers


class UserDataSource : UserRepository {

    private val compositeDisposable = CompositeDisposable()

    override fun getUsers(limit: Int): List<User> {
        var list: List<User>? = null
        compositeDisposable.add(
            RemoteInstance.getApi().getUsers(limit)
                .subscribeOn(Schedulers.io())
                .map {
                    it.results.map { it.toUser() }
                }
                .onErrorResumeNext {
                    LocalInstance.getDao().getAll()
                }.observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    list = it
                }, {
                    it.printStackTrace()
                })
        )

        return list!!
    }

    override fun saveUsers(users: List<User>) {
        compositeDisposable.add(
            LocalInstance.getDao().insertUsers(users).subscribeOn(Schedulers.io())
                .subscribe({}, { it.printStackTrace() })
        )
    }

    override fun getUserById(id: String): User {
        var user: User? = null
        LocalInstance.getDao().getUser(id).subscribe({
            user = it
        }, {
            it.printStackTrace()
        })
        return user!!
    }

    override fun fetchUsers(): List<User> {
        TODO("Not yet implemented")
    }
}