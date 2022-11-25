package com.raveline.borutoapp.domain.di

import android.content.Context
import androidx.room.Room
import com.raveline.borutoapp.data.database.HeroDatabase
import com.raveline.borutoapp.data.database.dao.HeroDao
import com.raveline.borutoapp.data.database.dao.HeroRemoteKeyDao
import com.raveline.borutoapp.data.repositoryImpl.LocalDataSourceImpl
import com.raveline.borutoapp.domain.repository.LocalDataSource
import com.raveline.borutoapp.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesDatabaseBuilder(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        HeroDatabase::class.java,
        Constants.heroDatabaseName
    ).fallbackToDestructiveMigration()
        .fallbackToDestructiveMigrationOnDowngrade()
        .allowMainThreadQueries()
        .build()

    @Provides
    @Singleton
    fun providesHeroDao(database: HeroDatabase): HeroDao = database.heroDao()

    @Provides
    @Singleton
    fun providesHeroKeyDao(database: HeroDatabase): HeroRemoteKeyDao = database.heroRemoteKeyDao()

    @Provides
    @Singleton
    fun providesLocalDataSource(heroDao: HeroDao): LocalDataSource = LocalDataSourceImpl(heroDao)
}