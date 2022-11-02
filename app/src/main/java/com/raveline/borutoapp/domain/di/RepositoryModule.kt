package com.raveline.borutoapp.domain.di

import android.content.Context
import com.raveline.borutoapp.data.repositoryImpl.DataStoreOperationsImpl
import com.raveline.borutoapp.data.repositoryImpl.Repository
import com.raveline.borutoapp.domain.repository.DataStoreOperations
import com.raveline.borutoapp.domain.use_cases.UseCases
import com.raveline.borutoapp.domain.use_cases.heroes.HeroesUseCase
import com.raveline.borutoapp.domain.use_cases.onboardings.OnBoardingUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providesDataStoreOperations(
        @ApplicationContext context: Context
    ): DataStoreOperations = DataStoreOperationsImpl(context)

    @Singleton
    @Provides
    fun providesOnBoardingUseCases(repository: Repository): UseCases =
        UseCases(
            OnBoardingUseCases(repository),
            HeroesUseCase(repository)
        )
}