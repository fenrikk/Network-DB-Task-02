package com.nikfen.network_db_task_02

import android.app.Application
import com.nikfen.network_db_task_02.di.AppComponent
import com.nikfen.network_db_task_02.di.DaggerAppComponent
import com.nikfen.network_db_task_02.di.data.DataModule

class UserApp : Application() {
    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().dataModule(DataModule(this)).build()
    }

    fun getAppComponent(): AppComponent = appComponent
}
