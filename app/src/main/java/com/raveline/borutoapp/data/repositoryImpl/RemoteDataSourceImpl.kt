package com.raveline.borutoapp.data.repositoryImpl

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.raveline.borutoapp.data.data_source.paging_datasource.HeroRemoteMediator
import com.raveline.borutoapp.data.data_source.paging_datasource.SearchHeroesSource
import com.raveline.borutoapp.data.database.HeroDatabase
import com.raveline.borutoapp.data.model.HeroModel
import com.raveline.borutoapp.data.remote.NarutoApi
import com.raveline.borutoapp.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow

class RemoteDataSourceImpl(
    private val narutoApi: NarutoApi,
    private val heroDatabase: HeroDatabase
) : RemoteDataSource {

    private val heroDao = heroDatabase.heroDao()

    @OptIn(ExperimentalPagingApi::class)
    override fun getAllHeroesData(): Flow<PagingData<HeroModel>> {
        val pagingSourceFactory = { heroDao.getAllHeroes() }
        return Pager(
            config = PagingConfig(pageSize = 3),
            remoteMediator = HeroRemoteMediator(
                narutoApi, heroDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override fun getSearchHeroes(query:String): Flow<PagingData<HeroModel>> {
        return Pager(
            config = PagingConfig(pageSize = 3),
            pagingSourceFactory = {
                SearchHeroesSource(narutoApi = narutoApi, query = query)
            }
        ).flow
    }
}