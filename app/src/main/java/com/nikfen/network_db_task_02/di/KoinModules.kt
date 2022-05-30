package com.nikfen.network_db_task_02.di

import androidx.room.Room
import com.nikfen.network_db_task_02.model.UserLoader
import com.nikfen.network_db_task_02.model.UserMain
import com.nikfen.network_db_task_02.model.UserMainRepository
import com.nikfen.network_db_task_02.model.local.UserLoaderLocalRepository
import com.nikfen.network_db_task_02.model.local.UserRepository
import com.nikfen.network_db_task_02.model.local.database.UserDatabase
import com.nikfen.network_db_task_02.model.remote.UserApi
import com.nikfen.network_db_task_02.model.remote.UserLoaderRemoteRepository
import com.nikfen.network_db_task_02.other.BASE_URL
import com.nikfen.network_db_task_02.other.LOCAL_DATABASE_NAME
import com.nikfen.network_db_task_02.viewmodel.UserFullViewViewModel
import com.nikfen.network_db_task_02.viewmodel.UserViewViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {
    single<UserRepository> {

        val database = Room.databaseBuilder(
            get(),
            UserDatabase::class.java, LOCAL_DATABASE_NAME
        ).build()

        UserLoaderLocalRepository(database.userDao())
    }

    single<UserLoader> {

        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()

        val userApi = retrofit.create(UserApi::class.java)

        UserLoaderRemoteRepository(userApi)
    }

    single<UserMain> { UserMainRepository(get(), get()) }
}

val viewModelModule = module {
    viewModel { UserViewViewModel(get()) }
    viewModel { UserFullViewViewModel(get()) }
}