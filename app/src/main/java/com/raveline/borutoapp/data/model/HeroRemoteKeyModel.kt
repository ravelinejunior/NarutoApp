package com.raveline.borutoapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.raveline.borutoapp.utils.Constants

@Entity(tableName = Constants.heroRemoteKeyTableName)
data class HeroRemoteKeyModel(
    @PrimaryKey(autoGenerate = false)
    val id:Int,
    val nextPage:Int?,
    val prevPage:Int?
)
