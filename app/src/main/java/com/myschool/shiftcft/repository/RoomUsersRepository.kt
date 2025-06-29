package com.myschool.shiftcft.repository

import com.myschool.shiftcft.database.UserDao
import com.myschool.shiftcft.model.User
import com.myschool.shiftcft.model.UserEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomUsersRepository(private val dao: UserDao) : DbUsersRepository {

    override fun getUsers(): Flow<List<User>> = dao.getAll()
        .map { it.map(UserEntity::toUser) }


    override fun saveUser(users: List<User>) {
        dao.saveAll(users.map{
            UserEntity.fromUser(it)
        })
    }

    override fun deleteAll() {
        dao.deleteAll()
    }

    override fun getUser(id: Long): User? {
        return if (dao.isEmpty()) {
            null
        } else {
            dao.getUser(id)?.toUser()
        }
    }
}