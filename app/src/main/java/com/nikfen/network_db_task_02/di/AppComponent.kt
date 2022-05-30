package com.nikfen.network_db_task_02.di

import com.nikfen.network_db_task_02.di.data.DataBinding
import com.nikfen.network_db_task_02.di.data.DataModule
import com.nikfen.network_db_task_02.di.viewModel.ViewModelModule
import com.nikfen.network_db_task_02.view.UserFullViewFragment
import com.nikfen.network_db_task_02.view.UserViewFragment
import dagger.Component

@Component(modules = [DataModule::class, DataBinding::class, ViewModelModule::class])
interface AppComponent {
    fun inject(userViewFragment: UserViewFragment)
    fun inject(userFullViewFragment: UserFullViewFragment)
}