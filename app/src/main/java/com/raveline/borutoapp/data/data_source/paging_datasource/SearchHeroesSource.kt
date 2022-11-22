package com.raveline.borutoapp.data.data_source.paging_datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.raveline.borutoapp.data.model.HeroModel
import com.raveline.borutoapp.data.remote.NarutoApi
import javax.inject.Inject

class SearchHeroesSource @Inject constructor(
    private val narutoApi: NarutoApi,
    private val query: String
) : PagingSource<Int, HeroModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, HeroModel> {
        return try {
            val response = narutoApi.searchHeroes(heroName = query)
            val heroes = response.heroes
            if (heroes.isNotEmpty()) {
                LoadResult.Page(
                    data = heroes,
                    prevKey = response.prevPage,
                    nextKey = response.nextPage
                )
            } else {
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, HeroModel>): Int? {
        return state.anchorPosition
    }
}