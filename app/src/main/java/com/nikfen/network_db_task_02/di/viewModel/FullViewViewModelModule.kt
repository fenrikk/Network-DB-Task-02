package com.nikfen.network_db_task_02.di.viewModel

import com.nikfen.network_db_task_02.model.local.UserRepository
import com.nikfen.network_db_task_02.viewmodel.UserFullViewViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class FullViewViewModelModule {

    @Provides
    fun provideViewModel(
        userRepository: UserRepository,
        id: String
    ): UserFullViewViewModel {
        return UserFullViewViewModel(userRepository, id)
    }
}