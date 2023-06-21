package com.sample.repository


import com.sample.models.CharacterResponse
import com.sample.network.Resource
import com.sample.network.CharactersApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepositoryImpl @Inject constructor(
    private val charactersApi: CharactersApi
) : MainRepository {

    override suspend fun getCharactersData(): Resource<CharacterResponse> {
        val response = charactersApi.getCharactersList()
        return if (response.isSuccessful)
            Resource.success(response.body())
        else
            Resource.error(response.message(), null)
    }
}