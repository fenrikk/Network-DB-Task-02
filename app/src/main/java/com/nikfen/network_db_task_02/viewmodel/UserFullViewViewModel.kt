package com.nikfen.network_db_task_02.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nikfen.network_db_task_02.model.local.UserRepository
import com.nikfen.network_db_task_02.model.local.tables.User
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class UserFullViewViewModel @AssistedInject constructor(
    userRepository: UserRepository,
    @Assisted id: String
) : BaseViewModel() {

    private val userItem = MutableLiveData<User>()

    init {
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

    @AssistedFactory
    interface Factory {
        fun build(id: String): UserFullViewViewModel
    }
}