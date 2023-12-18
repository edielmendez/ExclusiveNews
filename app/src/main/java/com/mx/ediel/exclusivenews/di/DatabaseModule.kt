package com.mx.ediel.exclusivenews.di

import android.content.Context
import com.mx.ediel.exclusivenews.data.local.dao.NewsDAO
import com.mx.ediel.exclusivenews.data.local.db.ExclusiveNewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Singleton
    @Provides
    fun provideExclusiveNewsDatabase(@ApplicationContext context: Context): ExclusiveNewsDatabase {
        return ExclusiveNewsDatabase.getInstance(context)
    }

    @Provides
    fun provideNewsDAO(appDatabase: ExclusiveNewsDatabase): NewsDAO {
        return appDatabase.newsDAO()
    }
}