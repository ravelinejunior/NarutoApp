package com.raveline.borutoapp.data.data_source.paging_datasource

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.raveline.borutoapp.data.database.HeroDatabase
import com.raveline.borutoapp.data.model.HeroModel
import com.raveline.borutoapp.data.model.HeroRemoteKeyModel
import com.raveline.borutoapp.data.remote.NarutoApi
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class HeroRemoteMediator @Inject constructor(
    private val narutoApi: NarutoApi,
    private val heroDatabase: HeroDatabase
) : RemoteMediator<Int, HeroModel>() {

    private val heroDao = heroDatabase.heroDao()
    private val heroRemoteKeyDao = heroDatabase.heroRemoteKeyDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, HeroModel>
    ): MediatorResult {
        return try {

            val page = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeyModel = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeyModel?.nextPage?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKey = getRemoteKeyForFirstItem(state)
                    val previousPage = remoteKey?.prevPage ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKey != null
                    )
                    previousPage
                }
                LoadType.APPEND -> {
                    val remoteKey = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKey?.nextPage ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKey != null
                    )
                    nextPage
                }
            }

            val response = narutoApi.getAllHeroes(page = page)
            if (response.heroes.isNotEmpty()) {
                // Allow you to make multiple operations inside the database
                heroDatabase.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        heroDao.deleteHeroes()
                        heroRemoteKeyDao.deleteRemoteKeys()
                    }

                    val previousPage = response.previousPage
                    val nextPage = response.nextPage
                    val keys = response.heroes.map { hero ->
                        HeroRemoteKeyModel(
                            id = hero.id,
                            prevPage = previousPage,
                            nextPage = nextPage
                        )
                    }
                    heroRemoteKeyDao.addAllRemoteKeys(keys)
                    heroDao.insertHeroes(heroes = response.heroes)
                }
            }

            MediatorResult.Success(endOfPaginationReached = response.nextPage == null)

        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }


    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, HeroModel>
    ): HeroRemoteKeyModel? =
        state.anchorPosition?.let { currentPosition ->
            state.closestItemToPosition(currentPosition)?.id?.let { id ->
                heroRemoteKeyDao.getRemoteKey(id = id)
            }
        }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, HeroModel>
    ): HeroRemoteKeyModel? =
        state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { heroModel ->
                heroRemoteKeyDao.getRemoteKey(id = heroModel.id)
            }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, HeroModel>
    ): HeroRemoteKeyModel? =
        state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { hero ->
                heroRemoteKeyDao.getRemoteKey(id = hero.id)
            }
}
