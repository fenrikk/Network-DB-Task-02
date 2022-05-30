package com.nikfen.network_db_task_02.di

import com.nikfen.network_db_task_02.model.UserLoader
import com.nikfen.network_db_task_02.model.UserMain
import com.nikfen.network_db_task_02.model.UserMainRepository
import com.nikfen.network_db_task_02.model.local.UserLoaderLocalRepository
import com.nikfen.network_db_task_02.model.local.UserRepository
import com.nikfen.network_db_task_02.model.remote.UserLoaderRemoteRepository
import dagger.Binds
import dagger.Module

@Module
interface DataBinding {
    @Binds
    fun bindLocalRepository(userLoaderLocalRepository: UserLoaderLocalRepository): UserRepository

    @Binds
    fun bindRemoteRepository(userLoaderRemoteRepository: UserLoaderRemoteRepository): UserLoader

    @Binds
    fun bindMainRepository(userMainRepository: UserMainRepository): UserMain
}