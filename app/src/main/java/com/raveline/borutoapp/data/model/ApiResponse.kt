package com.raveline.borutoapp.data.model

@kotlinx.serialization.Serializable
data class ApiResponse(
    val success:Boolean,
    val message:String? = null,
    val previousPage:Int? = null,
    val nextPage:Int? = null,
    val heroes:List<HeroModel> = emptyList()
)
