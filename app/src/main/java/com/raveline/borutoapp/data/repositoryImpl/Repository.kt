package com.raveline.borutoapp.data.repositoryImpl

import androidx.paging.PagingData
import com.raveline.borutoapp.data.model.HeroModel
import com.raveline.borutoapp.domain.repository.DataStoreOperations
import com.raveline.borutoapp.domain.repository.LocalDataSource
import com.raveline.borutoapp.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val dataStore: DataStoreOperations,
    private val local: LocalDataSource
) {

    fun getAllHeroesData(): Flow<PagingData<HeroModel>> =
        remoteDataSource.getAllHeroesData()

    fun searchHeroes(query: String): Flow<PagingData<HeroModel>> =
        remoteDataSource.getSearchHeroes(query)

    suspend fun getSelectedHero(heroId: Int): HeroModel =
        local.getSelectedHeroById(heroId = heroId)

    suspend fun saveOnBoardingState(completed: Boolean) {
        dataStore.saveOnBoardingProcessState(completed)
    }

    fun readOnBoardingState(): Flow<Boolean> = dataStore.readOnBoardingState()
}