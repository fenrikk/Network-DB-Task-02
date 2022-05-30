package com.nikfen.network_db_task_02.di.viewModel

import com.nikfen.network_db_task_02.viewmodel.UserFullViewViewModel
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(modules = [FullViewViewModelModule::class])

interface FullViewViewModelSubcomponent {

    val userFullViewViewModel: UserFullViewViewModel

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance id: String): FullViewViewModelSubcomponent
    }
}