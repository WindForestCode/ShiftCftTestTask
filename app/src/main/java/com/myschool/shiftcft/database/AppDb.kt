package com.myschool.shiftcft.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.myschool.shiftcft.model.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class AppDb : RoomDatabase() {
    abstract val userDao: UserDao

}