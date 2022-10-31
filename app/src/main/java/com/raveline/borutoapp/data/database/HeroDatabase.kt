package com.raveline.borutoapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.raveline.borutoapp.data.database.converter.HeroDatabaseConverter
import com.raveline.borutoapp.data.database.dao.HeroDao
import com.raveline.borutoapp.data.database.dao.HeroRemoteKeyDao
import com.raveline.borutoapp.data.model.HeroModel
import com.raveline.borutoapp.data.model.HeroRemoteKeyModel

@Database(
    entities = [HeroModel::class,
        HeroRemoteKeyModel::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(HeroDatabaseConverter::class)
abstract class HeroDatabase : RoomDatabase() {
    abstract fun heroDao(): HeroDao
    abstract fun heroRemoteKeyDao():HeroRemoteKeyDao
}