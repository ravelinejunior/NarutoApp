package com.raveline.borutoapp.domain.repository

import com.raveline.borutoapp.data.model.HeroModel

interface LocalDataSource {
    suspend fun getSelectedHeroById(heroId:Int):HeroModel
}