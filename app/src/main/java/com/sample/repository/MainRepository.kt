package com.sample.repository

import com.sample.models.CharacterResponse
import com.sample.network.Resource

interface MainRepository {
    suspend fun getCharactersData():Resource<CharacterResponse>
}