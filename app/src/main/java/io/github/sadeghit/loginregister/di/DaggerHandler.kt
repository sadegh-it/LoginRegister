package io.github.sadeghit.loginregister.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.sadeghit.loginregister.data.remote.ApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaggerHandler {

    @Provides
    @Singleton
    fun providesClient(): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(3, TimeUnit.SECONDS) //Connection
            .readTimeout(3, TimeUnit.SECONDS) //Response
            .writeTimeout(3, TimeUnit.SECONDS) // Request
            .build()

    @Provides
    @Singleton
    fun providePostRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)
}