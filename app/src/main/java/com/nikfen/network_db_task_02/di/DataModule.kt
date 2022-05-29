package com.nikfen.network_db_task_02.di

import android.content.Context
import com.nikfen.network_db_task_02.model.UserLoader
import com.nikfen.network_db_task_02.model.UserMainRepository
import com.nikfen.network_db_task_02.model.local.UserLoaderLocalRepository
import com.nikfen.network_db_task_02.model.local.UserRepository
import com.nikfen.network_db_task_02.model.local.dao.UserDao
import com.nikfen.network_db_task_02.model.local.database.UserDatabase
import com.nikfen.network_db_task_02.model.remote.RetrofitClient
import com.nikfen.network_db_task_02.model.remote.UserApi
import com.nikfen.network_db_task_02.model.remote.UserLoaderRemoteRepository
import dagger.Module
import dagger.Provides

@Module
class DataModule(private val applicationContext: Context) {
    @Provides
    fun provideMainRepository(
        userRepository: UserRepository,
        userLoader: UserLoader
    ): UserMainRepository = UserMainRepository(userRepository, userLoader)

    @Provides
    fun provideRemote(userApi: UserApi): UserLoader = UserLoaderRemoteRepository(userApi)

    @Provides
    fun provideUserApi(): UserApi = RetrofitClient.getApi()

    @Provides
    fun provideLocal(userDao: UserDao): UserRepository = UserLoaderLocalRepository(userDao)

    @Provides
    fun provideDao(): UserDao = UserDatabase.getInstance(applicationContext).userDao()
}