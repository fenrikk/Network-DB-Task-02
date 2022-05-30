package com.nikfen.network_db_task_02.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nikfen.network_db_task_02.model.local.UserRepository
import com.nikfen.network_db_task_02.model.local.tables.User
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class UserFullViewViewModel(
    private val userRepository: UserRepository,
) : BaseViewModel() {

    private val userItem = MutableLiveData<User>()

    fun fetchUser(id: String) {
        userRepository.getUserById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                userItem.value = it
            }, {
                it.printStackTrace()
            }).autoDispose()
    }

    fun getUser(): LiveData<User> = userItem
}