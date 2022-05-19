package com.nikfen.network_db_task_02.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nikfen.network_db_task_02.model.local.dao.UserDao
import com.nikfen.network_db_task_02.model.local.tables.User
import com.nikfen.network_db_task_02.model.remote.UserApi
import com.nikfen.network_db_task_02.model.remote.response.toUser
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class UserViewViewModel(
    private val userApi: UserApi,
    private val userDao: UserDao
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val userLiveDataList: MutableLiveData<List<User>> = MutableLiveData<List<User>>()

    init {
        compositeDisposable.add(
            userApi.getUsers(25)
                .subscribeOn(Schedulers.io())
                .map {
                    it.results.map { it.toUser() }
                }.flatMap { users ->
                    userDao.insertUsers(users)
                        .andThen(Single.just(users))
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    userLiveDataList.value = it
                }, {

                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun getUserList(): LiveData<List<User>> {
        return userLiveDataList
    }
}