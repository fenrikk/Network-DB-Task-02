package com.nikfen.network_db_task_02.model

import com.nikfen.network_db_task_02.model.local.UserRepository
import com.nikfen.network_db_task_02.model.local.tables.User
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject
import javax.inject.Named

class UserMainRepository @Inject constructor(
    @Named("local")
    private val userLocal: UserRepository,
    private val userRemote: UserLoader
) : UserRepository {
    override fun saveUsers(users: List<User>): Completable {
        return userLocal.saveUsers(users)
    }

    override fun getUserById(id: String): Single<User> {
        return userLocal.getUserById(id)
    }

    override fun clearBase(): Completable {
        return userLocal.clearBase()
    }

    override fun getUsers(page: Int, limit: Int): Single<List<User>> {
        return userRemote.getUsers(page, limit)
            .flatMap { users ->
                if (page == 1) {
                    userLocal.clearBase()
                        .andThen(userLocal.saveUsers(users))
                        .andThen(Single.just(users))
                } else userLocal.saveUsers(users).andThen(Single.just(users))
            }.onErrorResumeNext { userLocal.getUsers(page, limit) }

    }
}