package com.sample.network


import com.sample.BuildConfig
import com.sample.models.CharacterResponse
import retrofit2.Response
import retrofit2.http.GET


interface CharactersApi {

    @GET("?q=${BuildConfig.QUERY}&format=json")
    suspend fun getCharactersList(): Response<CharacterResponse>
}