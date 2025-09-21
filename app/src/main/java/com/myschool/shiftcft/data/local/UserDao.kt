package com.myschool.shiftcft.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM users ORDER BY id DESC")
    fun getAll(): Flow<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun saveAll(users: List<UserEntity>): List<Long>

    @Query("DELETE FROM users")
    suspend fun deleteAll()

    @Query("SELECT * FROM users WHERE id = :id")
    suspend fun getUser(id: Long): UserEntity?

    @Query("SELECT COUNT(*) FROM users")
    suspend fun getCount(): Long

    suspend fun isEmpty(): Boolean {
        return getCount() == 0L
    }

}