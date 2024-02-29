package com.example.simpsons.network

import com.example.simpsons.models.Characters
import retrofit2.Response

import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    @GET("quotes?count=12")
    suspend fun getCharacters(): Response<List<Characters>>

    @GET("quotes")
    suspend fun getCharacter(
        @Query("character") character : String
    ): Response<List<Characters>>
}