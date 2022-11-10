package com.raveline.borutoapp.domain.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.raveline.borutoapp.data.database.HeroDatabase
import com.raveline.borutoapp.data.remote.NarutoApi
import com.raveline.borutoapp.data.repositoryImpl.RemoteDataSourceImpl
import com.raveline.borutoapp.domain.repository.RemoteDataSource
import com.raveline.borutoapp.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesClientHttp(): OkHttpClient =
        OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.MINUTES)
            .connectTimeout(15, TimeUnit.MINUTES)
            .build()

    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    @Singleton
    fun providesRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {
        val contentType = "application/json".toMediaType()
        val json = Json{ignoreUnknownKeys = true}
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
    }

    @Provides
    @Singleton
    fun providesNarutoApi(retrofit: Retrofit): NarutoApi = retrofit.create(NarutoApi::class.java)

    @Provides
    @Singleton
    fun providesRemoteDataSource(
        narutoApi: NarutoApi,
        heroDatabase: HeroDatabase
    ): RemoteDataSource = RemoteDataSourceImpl(
        narutoApi, heroDatabase
    )

}