package com.nikfen.network_db_task_02.di.viewModel

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module(
    subcomponents = [FullViewViewModelSubcomponent::class]
)
@InstallIn(ActivityComponent::class)
class ViewModelModule