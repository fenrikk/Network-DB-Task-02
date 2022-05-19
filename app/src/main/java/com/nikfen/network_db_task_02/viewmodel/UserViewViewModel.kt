package com.nikfen.network_db_task_02.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nikfen.network_db_task_02.model.local.dao.UserDao
import com.nikfen.network_db_task_02.model.local.tables.User
import com.nikfen.network_db_task_02.model.remote.RemoteInstance
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class UserViewViewModel(
    private val userDao: UserDao
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val userLiveDataList: MutableLiveData<List<User>> = MutableLiveData<List<User>>()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun fetchUserList() {
        RemoteInstance.getApi().let {
            compositeDisposable.add(
                RemoteInstance.getApi().getUsers()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        for (i in it.results.iterator()) {
                            userDao.insertUser(
                                User(
                                    0,
                                    i.name.first,
                                    i.name.last,
                                    i.dob.age,
                                    i.email,
                                    i.phone,
                                    i.gender,
                                    i.picture.large
                                )
                            )
                        }
                    }, {

                    })
            )
        }
        userDao.getAll().let {
            compositeDisposable.add(
                userDao.getAll().subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe({
                        userLiveDataList.value = it
                    }, {

                    }
                    ))
        }
    }

    fun getUserList(): LiveData<List<User>> {
        return userLiveDataList
    }
}