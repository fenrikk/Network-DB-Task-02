package com.nikfen.network_db_task_02.di.data

import com.nikfen.network_db_task_02.model.UserLoader
import com.nikfen.network_db_task_02.model.UserMainRepository
import com.nikfen.network_db_task_02.model.local.UserLoaderLocalRepository
import com.nikfen.network_db_task_02.model.local.UserRepository
import com.nikfen.network_db_task_02.model.remote.UserLoaderRemoteRepository
import dagger.Binds
import dagger.Module
import javax.inject.Named

@Module
interface DataBinding {

    @Binds
    fun bindRemoteRepository(userLoaderRemoteRepository: UserLoaderRemoteRepository): UserLoader

    @Named("local")
    @Binds
    fun bindLocalRepository(userLoaderLocalRepository: UserLoaderLocalRepository): UserRepository

    @Binds
    fun bindMainRepository(userMainRepository: UserMainRepository): UserRepository
}