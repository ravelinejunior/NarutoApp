package com.raveline.borutoapp.data.repositoryImpl

import com.raveline.borutoapp.data.database.dao.HeroDao
import com.raveline.borutoapp.data.model.HeroModel
import com.raveline.borutoapp.domain.repository.LocalDataSource
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val heroDao: HeroDao
) : LocalDataSource {

    override suspend fun getSelectedHeroById(heroId: Int): HeroModel =
        heroDao.getHeroById(heroId = heroId)
}