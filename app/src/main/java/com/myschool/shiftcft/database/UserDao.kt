package com.myschool.shiftcft.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.myschool.shiftcft.model.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM users ORDER BY id DESC")
    fun getAll(): Flow<List<UserEntity>>

    @Upsert()
    fun save(user: UserEntity): Long

    @Query("DELETE FROM users")
    fun deleteAll()

    @Query("SELECT * FROM users ORDER BY RANDOM() LIMIT 1")
    fun getUser(): UserEntity?

    @Query("SELECT COUNT(*) FROM users")
    fun getCount(): Int

    fun isEmpty(): Boolean {
        return getCount() == 0
    }

}