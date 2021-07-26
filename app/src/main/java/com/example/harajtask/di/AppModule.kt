package com.example.harajtask.di

import android.app.Application
import android.content.Context
import com.example.harajtask.data.ApiService
import com.example.harajtask.data.AppDataManager
import com.example.harajtask.data.room.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    internal fun provideDataManager(context:Context, apiService: ApiService): AppDataManager {
        return AppDataManager(context, apiService)
    }

    @Provides
    @Singleton
    internal fun provideApiHelper(): ApiService = ApiService.create()

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

}