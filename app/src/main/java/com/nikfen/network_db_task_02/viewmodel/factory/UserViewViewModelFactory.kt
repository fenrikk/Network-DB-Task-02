package com.nikfen.network_db_task_02.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nikfen.network_db_task_02.model.local.UserRepository
import com.nikfen.network_db_task_02.viewmodel.UserViewViewModel
import javax.inject.Inject
import javax.inject.Named

class UserViewViewModelFactory @Inject constructor(
    @Named("main")
    val userMainRepository: UserRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserViewViewModel(userMainRepository) as T
    }
}