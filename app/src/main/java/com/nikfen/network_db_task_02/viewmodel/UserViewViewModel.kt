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
        loadUsers()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun getUserList(): LiveData<List<User>> {
        return userLiveDataList
    }

    fun loadUsers() {
        compositeDisposable.add(
            userApi.getUsers(25)
                .subscribeOn(Schedulers.io())
                .map {
                    it.results.map { it.toUser() }
                }.flatMap { users ->
                    val insertCompletable = if (userLiveDataList.value?.isEmpty() != false){
                        userDao.clearTable().andThen(userDao.insertUsers(users))
                    }
                    else{
                        userDao.insertUsers(users)
                    }
                    insertCompletable.andThen(Single.just(users))
                }.onErrorResumeNext {
                    userDao.getAll()
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    val currentUsers = userLiveDataList.value ?: emptyList()
                    userLiveDataList.value = currentUsers + it
                }, {
                    it.printStackTrace()
                })
        )
    }
}