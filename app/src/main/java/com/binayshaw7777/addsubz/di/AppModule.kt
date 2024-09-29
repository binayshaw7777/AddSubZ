package com.binayshaw7777.addsubz.di

import android.content.Context
import com.binayshaw7777.addsubz.data.local.DataStoreUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideDataStoreUtil(@ApplicationContext context: Context): DataStoreUtil =
        DataStoreUtil(context)

    @Singleton
    @Provides
    fun provideDataStore(dataStoreUtil: DataStoreUtil) = dataStoreUtil.dataStore
}