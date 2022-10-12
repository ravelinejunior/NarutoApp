package com.raveline.borutoapp.domain.di

import android.content.Context
import androidx.room.Room
import com.raveline.borutoapp.data.database.HeroDatabase
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
}