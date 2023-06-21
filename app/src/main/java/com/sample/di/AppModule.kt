package com.sample.di

import com.sample.BuildConfig.BASE_URL
import com.sample.network.CharactersApi
import com.sample.repository.MainRepository
import com.sample.repository.MainRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesOkHttpClient(): OkHttpClient = OkHttpClient.Builder().build()


    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL).client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun providesSimpsonsApiService(retrofit: Retrofit): CharactersApi = retrofit
        .create(CharactersApi::class.java)

    @Provides
    @Singleton
    fun providesMainRepository(charactersApi: CharactersApi): MainRepository =
        MainRepositoryImpl(charactersApi)

}