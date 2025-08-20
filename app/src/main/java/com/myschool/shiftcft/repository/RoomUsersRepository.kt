package com.myschool.shiftcft.repository

import com.myschool.shiftcft.database.UserDao
import com.myschool.shiftcft.model.User
import com.myschool.shiftcft.model.UserEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomUsersRepository(private val dao: UserDao) : DbUsersRepository {

    override fun getUsers(): Flow<List<User>> = dao.getAll()
        .map { it.map(UserEntity::toUser) }


    override suspend fun saveUser(users: List<User>) {
        dao.saveAll(users.map {
            UserEntity.fromUser(it)
        })
    }

    override suspend fun deleteAll() {
        dao.deleteAll()
    }

    override suspend fun getUser(id: Long): User? {
        return if (dao.isEmpty()) {
            null
        } else {
            return dao.getUser(id)?.toUser()
        }
    }

    override suspend fun getCount(): Long = dao.getCount()

    override suspend fun isEmpty(): Boolean = dao.isEmpty()


}