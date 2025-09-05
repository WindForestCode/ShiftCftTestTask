package com.myschool.shiftcft.database.di

import android.content.Context
import androidx.room.Room
import com.myschool.shiftcft.database.AppDb
import com.myschool.shiftcft.database.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DbModule {

    @Provides
    @Singleton
    fun provideAppDataBase(@ApplicationContext context: Context): AppDb =
        Room.databaseBuilder(
            context,
            AppDb::class.java,
            "app_db"
        )
            .fallbackToDestructiveMigration(false)
            .build()
    @Provides
    fun provideUserDao(database: AppDb): UserDao = database.userDao

}