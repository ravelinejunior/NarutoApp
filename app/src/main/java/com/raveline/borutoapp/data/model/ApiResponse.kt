package com.raveline.borutoapp.data.model

@kotlinx.serialization.Serializable
data class ApiResponse(
    val success:Boolean,
    val message:String? = null,
    val prevPage:Int? = null,
    val nextPage:Int? = null,
    val heroes:List<HeroModel> = emptyList(),
    val lastUpdated: Long? = null,
)
