package com.raveline.borutoapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.raveline.borutoapp.data.model.HeroRemoteKeyModel

@Dao
interface HeroRemoteKeyDao {
    @Query("SELECT * FROM HERO_REMOTE_KEY_TABLE WHERE id == :id")
    suspend fun getRemoteKey(id:Int):HeroRemoteKeyModel?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(heroRemoteKeys:List<HeroRemoteKeyModel>)

    @Query("DELETE FROM HERO_REMOTE_KEY_TABLE")
    suspend fun deleteRemoteKeys()
}