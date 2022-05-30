package com.nikfen.network_db_task_02.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nikfen.network_db_task_02.model.UserMain
import com.nikfen.network_db_task_02.model.local.tables.User
import com.nikfen.network_db_task_02.other.FETCH_VALUE
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class UserViewViewModel(
    private val userMainRepository: UserMain
) : BaseViewModel() {

    private val userLiveDataList: MutableLiveData<List<User>> = MutableLiveData<List<User>>()
    private var page = 0

    init {
        loadUsers()
    }

    fun getUserList(): LiveData<List<User>> {
        return userLiveDataList
    }

    fun loadUsers() {
        userMainRepository.getUsers(page, FETCH_VALUE)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                page++
                val currentUsers = userLiveDataList.value ?: emptyList()
                userLiveDataList.value = currentUsers + it
            }, {
                it.printStackTrace()
            }).autoDispose()
    }
}