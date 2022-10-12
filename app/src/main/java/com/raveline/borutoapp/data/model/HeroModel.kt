package com.raveline.borutoapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.raveline.borutoapp.utils.Constants.heroTableName


@Entity(tableName = heroTableName)
data class HeroModel(
    @PrimaryKey(autoGenerate = false)
    val id: Int,

    val heroName: String,
    val heroImage: String,
    val heroAbout: String,
    val heroRating: Double,
    val heroPower: Int,
    val month: String,
    val day: String,
    val heroFamily: List<String>,
    val heroAbilities: List<String>,
    val heroNatureTypes: List<String>
)
