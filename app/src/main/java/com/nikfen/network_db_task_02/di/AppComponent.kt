package com.nikfen.network_db_task_02.di

import com.nikfen.network_db_task_02.view.UserFullViewFragment
import com.nikfen.network_db_task_02.view.UserViewFragment
import dagger.Component

@Component(modules = [DataModule::class])
interface AppComponent {
    fun inject(userViewFragment: UserViewFragment)
    fun inject(userFullViewFragment: UserFullViewFragment)
}