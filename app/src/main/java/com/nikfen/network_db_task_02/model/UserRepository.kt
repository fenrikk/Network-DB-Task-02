package com.nikfen.network_db_task_02.model

import com.nikfen.network_db_task_02.model.local.UserLocal
import com.nikfen.network_db_task_02.model.remote.UserRemote

interface UserRepository: UserLocal, UserRemote