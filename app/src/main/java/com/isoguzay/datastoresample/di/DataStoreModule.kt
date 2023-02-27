package com.isoguzay.datastoresample.di

import android.content.Context
import androidx.datastore.core.DataStore
import com.isoguzay.datastoresample.data.local.datastore.localConfigDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun providesLocalConfigDataStore(@ApplicationContext context: Context) = context.localConfigDataStore

}