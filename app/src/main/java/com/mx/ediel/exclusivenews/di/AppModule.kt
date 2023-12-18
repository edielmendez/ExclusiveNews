package com.mx.ediel.exclusivenews.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mx.ediel.exclusivenews.data.local.repositories.NewsLocalRepository
import com.mx.ediel.exclusivenews.data.local.repositories.NewsLocalRepositoryImpl
import com.mx.ediel.exclusivenews.data.remote.common.RemoteServiceConstants.BASE_URL
import com.mx.ediel.exclusivenews.data.remote.news.NewsRepository
import com.mx.ediel.exclusivenews.data.remote.news.NewsRepositoryImpl
import com.mx.ediel.exclusivenews.data.remote.service.AppService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideBaseUrl() = BASE_URL

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().setLenient().create()

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
        return OkHttpClient
            .Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String, gson: Gson, client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    @Singleton
    @Provides
    fun provideAppService(retrofit: Retrofit): AppService =  retrofit.create(AppService::class.java)

    @Provides
    @Singleton
    fun provideNewsRepository(newsRepositoryImpl: NewsRepositoryImpl): NewsRepository = newsRepositoryImpl

    @Provides
    @Singleton
    fun provideNewsLocalRepository(newsLocalRepositoryImpl: NewsLocalRepositoryImpl): NewsLocalRepository = newsLocalRepositoryImpl

    @Provides
    @DefaultDispatcher
    fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.IO
}
annotation class DefaultDispatcher