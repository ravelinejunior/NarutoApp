package com.raveline.borutoapp.data.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.raveline.borutoapp.data.model.HeroModel

@Dao
interface HeroDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHeroes(heroes: List<HeroModel>)

    @Query("DELETE FROM HERO_TABLE")
    suspend fun deleteHeroes()

    @Query("SELECT * FROM HERO_TABLE ORDER BY id ASC")
    fun getAllHeroes(): PagingSource<Int, HeroModel>

    @Query("SELECT * FROM HERO_TABLE WHERE id == :heroId")
    fun getHeroById(heroId: Int): HeroModel

}