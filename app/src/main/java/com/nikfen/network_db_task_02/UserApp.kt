package com.nikfen.network_db_task_02

import android.app.Application
import com.nikfen.network_db_task_02.di.dataModule
import com.nikfen.network_db_task_02.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class UserApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@UserApp)
            modules(listOf(dataModule, viewModelModule))
        }
    }
}