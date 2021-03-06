package com.nikfen.network_db_task_02.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nikfen.network_db_task_02.model.UserMainRepository
import com.nikfen.network_db_task_02.viewmodel.UserViewViewModel

class UserViewViewModelFactory(
    private val userMainRepository: UserMainRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserViewViewModel(userMainRepository) as T
    }
}