package com.nikfen.network_db_task_02.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nikfen.network_db_task_02.model.UserRequestMainRepository
import com.nikfen.network_db_task_02.model.local.tables.User
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class UserViewViewModel(
    private val userMainRepository: UserRequestMainRepository
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val userLiveDataList: MutableLiveData<List<User>> = MutableLiveData<List<User>>()

    init {
        loadUsers()
    }

    fun getUserList(): LiveData<List<User>> {
        return userLiveDataList
    }

    fun loadUsers() {
        compositeDisposable.add(
            userMainRepository.getUsers(25)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    val currentUsers = userLiveDataList.value ?: emptyList()
                    userLiveDataList.value = currentUsers + it
                }, {
                    it.printStackTrace()
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}