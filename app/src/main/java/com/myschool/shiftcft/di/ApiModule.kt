package com.myschool.shiftcft.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.myschool.shiftcft.data.remote.UsersApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ApiModule {
    private companion object {
        private val contentType = "application/json".toMediaType()
        private val json = Json { ignoreUnknownKeys = true }
    }
        @Singleton
        @Provides
        fun provideOkHttp(): OkHttpClient =  OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .addInterceptor {
                it.proceed(
                    it.request()
                        .newBuilder()
                        .build()
                )
            }
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .client(provideOkHttp())
            .baseUrl("https://randomuser.me/")
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
    @Provides
    @Singleton
    fun provideUsersApi(retrofit: Retrofit): UsersApi =
        retrofit.create(UsersApi::class.java)


}