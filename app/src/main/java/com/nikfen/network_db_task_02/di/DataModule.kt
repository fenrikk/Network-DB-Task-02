package com.nikfen.network_db_task_02.di

import android.content.Context
import androidx.room.Room
import com.nikfen.network_db_task_02.model.local.dao.UserDao
import com.nikfen.network_db_task_02.model.local.database.UserDatabase
import com.nikfen.network_db_task_02.model.remote.UserApi
import com.nikfen.network_db_task_02.other.BASE_URL
import com.nikfen.network_db_task_02.other.LOCAL_DATABASE_NAME
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class DataModule(private val applicationContext: Context) {

    @Provides
    fun provideUserApi(): UserApi {
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

        return retrofit.create(UserApi::class.java)
    }

    @Provides
    fun provideDao(userDatabase: UserDatabase): UserDao = userDatabase.userDao()

    @Provides
    fun provideDataBase(): UserDatabase {
        return Room.databaseBuilder(
            applicationContext,
            UserDatabase::class.java, LOCAL_DATABASE_NAME
        ).build()
    }
}