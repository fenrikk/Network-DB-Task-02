package com.nikfen.network_db_task_02.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nikfen.network_db_task_02.model.local.dao.UserDao
import com.nikfen.network_db_task_02.model.remote.UserApi
import com.nikfen.network_db_task_02.viewmodel.UserViewViewModel

class UserViewViewModelFactory(
    private val userApi: UserApi,
    private val userDao: UserDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserViewViewModel(userApi, userDao) as T
    }
}