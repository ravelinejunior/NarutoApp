package com.raveline.borutoapp.data.database.converter

import androidx.room.TypeConverter

class HeroDatabaseConverter {
    private val charSeparator = ","

    @TypeConverter
    fun convertListToString(list: List<String>): String {
        val stringBuilder = StringBuilder()
        for (item in list) {
            stringBuilder.append(item).append(charSeparator)
        }

        //remove the last comma
        stringBuilder.setLength(stringBuilder.length - charSeparator.length)
        return stringBuilder.toString()
    }

    @TypeConverter
    fun convertStringToList(value: String): List<String> =
        value.split(charSeparator)
}