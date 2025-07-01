package com.myschool.shiftcft.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.myschool.shiftcft.model.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM users ORDER BY id DESC")
    fun getAll(): Flow<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAll(users: List<UserEntity>): List<Long>

    @Query("DELETE FROM users")
    fun deleteAll()

    @Query("SELECT * FROM users WHERE id = :id")
    fun getUser(id: Long): UserEntity?

    @Query("SELECT COUNT(*) FROM users")
    fun getCount(): Long

    fun isEmpty(): Boolean {
        return getCount() == 0L
    }

}