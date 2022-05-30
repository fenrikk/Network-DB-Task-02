package com.nikfen.network_db_task_02.di

import com.nikfen.network_db_task_02.model.UserLoader
import com.nikfen.network_db_task_02.model.UserMain
import com.nikfen.network_db_task_02.model.UserMainRepository
import com.nikfen.network_db_task_02.model.local.UserLoaderLocalRepository
import com.nikfen.network_db_task_02.model.local.UserRepository
import com.nikfen.network_db_task_02.model.local.database.UserDatabase
import com.nikfen.network_db_task_02.model.remote.RetrofitClient
import com.nikfen.network_db_task_02.model.remote.UserLoaderRemoteRepository
import com.nikfen.network_db_task_02.viewmodel.UserFullViewViewModel
import com.nikfen.network_db_task_02.viewmodel.UserViewViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dataModule = module {
    single<UserRepository> { UserLoaderLocalRepository(UserDatabase.getInstance(get()).userDao()) }
    single<UserLoader> { UserLoaderRemoteRepository(RetrofitClient.getApi()) }
    single<UserMain> { UserMainRepository(get(), get()) }
}

val viewModelModule = module {
    viewModel { UserViewViewModel(get()) }
    viewModel { UserFullViewViewModel(get()) }
}