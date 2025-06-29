package com.myschool.shiftcft.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.myschool.shiftcft.model.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class AppDb : RoomDatabase() {
    abstract val userDao: UserDao

    companion object {
        @Volatile
        private var INSTANCE: AppDb? = null
        fun getInstance(context: Context): AppDb {
            INSTANCE?.let { return it }

            val application = context.applicationContext

            synchronized(this) {
                INSTANCE?.let { return it }
            }

            val appDb = Room.databaseBuilder(application, AppDb::class.java, "app_db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()

            INSTANCE = appDb
            return appDb
        }
    }

}