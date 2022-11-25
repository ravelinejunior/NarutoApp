package com.raveline.borutoapp.domain.use_cases.heroes

import androidx.paging.PagingData
import com.raveline.borutoapp.data.model.HeroModel
import com.raveline.borutoapp.data.repositoryImpl.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HeroesUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(): Flow<PagingData<HeroModel>> =
        repository.getAllHeroesData()

    operator fun invoke(query: String): Flow<PagingData<HeroModel>> =
        repository.searchHeroes(query)

    suspend operator fun invoke(heroId: Int): HeroModel =
        repository.getSelectedHero(heroId = heroId)
}