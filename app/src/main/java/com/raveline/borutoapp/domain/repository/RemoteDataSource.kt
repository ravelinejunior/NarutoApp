package com.raveline.borutoapp.domain.repository

import androidx.paging.PagingData
import com.raveline.borutoapp.data.model.HeroModel
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun getAllHeroesData(): Flow<PagingData<HeroModel>>
    fun getSearchHeroes(query: String): Flow<PagingData<HeroModel>>
}