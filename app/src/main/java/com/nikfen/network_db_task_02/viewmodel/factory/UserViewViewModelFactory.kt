package com.nikfen.network_db_task_02.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nikfen.network_db_task_02.model.UserDataSource
import com.nikfen.network_db_task_02.viewmodel.UserViewViewModel

class UserViewViewModelFactory(
    private val userDataSource: UserDataSource
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserViewViewModel(userDataSource) as T
    }
}