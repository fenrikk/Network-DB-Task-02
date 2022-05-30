package com.nikfen.network_db_task_02.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nikfen.network_db_task_02.di.viewModel.FullViewViewModelSubcomponent

class UserFullViewViewModelFactory(
    private val factory: FullViewViewModelSubcomponent.Factory,
    private val id: String
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return factory.create(id).userFullViewViewModel as T
    }
}