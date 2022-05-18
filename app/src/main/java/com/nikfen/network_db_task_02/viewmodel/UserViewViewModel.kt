package com.nikfen.network_db_task_02.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nikfen.network_db_task_02.model.remote.RemoteInstance
import com.nikfen.network_db_task_02.model.remote.UserApi
import com.nikfen.network_db_task_02.model.remote.response.UserData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class UserViewViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val userList: MutableLiveData<List<UserData>> = MutableLiveData<List<UserData>>()

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
                        userList.value = it.data
                    }, {

                    })
            )
        }
    }

    fun getUserList(): LiveData<List<UserData>> {
        return userList
    }
}