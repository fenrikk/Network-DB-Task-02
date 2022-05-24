package com.nikfen.network_db_task_02.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nikfen.network_db_task_02.model.UserDataSource
import com.nikfen.network_db_task_02.model.local.dao.UserDao
import com.nikfen.network_db_task_02.model.local.tables.User
import com.nikfen.network_db_task_02.model.remote.UserApi
import com.nikfen.network_db_task_02.model.remote.response.toUser
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class UserViewViewModel(
    val userDataSource: UserDataSource
) : ViewModel() {

    private val userLiveDataList: MutableLiveData<List<User>> = MutableLiveData<List<User>>()

    init {
        loadUsers()
    }

    fun getUserList(): LiveData<List<User>> {
        return userLiveDataList
    }

    fun loadUsers() {
        val loaded = userDataSource.getUsers(10)
        userLiveDataList.value = userLiveDataList.value?.plus(loaded)
        userDataSource.saveUsers(loaded)
    }
}