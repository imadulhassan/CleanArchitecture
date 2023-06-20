package com.sample.repo


import com.sample.base.BaseResponse
import com.sample.repo.network.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor( private val apiService: ApiService) {

    suspend  fun getCharacters():  Resource<BaseResponse> {
        return   apiService.getCharactersList().let {response->
            if (response.isSuccessful) Resource.success(response.body()) else  Resource.error(response.message(),null)
        }
    }

}