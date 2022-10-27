package com.raveline.borutoapp.data.remote

import com.raveline.borutoapp.data.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NarutoApi {

    @GET("/naruto/heroes")
    suspend fun getAllHeroes(
        @Query("page") page: Int = 1
    ): ApiResponse

    @GET("/naruto/heroes/search")
    suspend fun searchHeroes(
        @Query("heroName") heroName: String
    ): ApiResponse
}