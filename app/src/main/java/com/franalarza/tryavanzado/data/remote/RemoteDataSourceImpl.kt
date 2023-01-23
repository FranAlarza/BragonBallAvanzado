package com.franalarza.tryavanzado.data.remote

import com.franalarza.tryavanzado.HeroesState
import com.franalarza.tryavanzado.data.remote.request.HeroRequest
import com.franalarza.tryavanzado.data.remote.response.HeroResponse
import javax.inject.Inject


class RemoteDataSourceImpl @Inject constructor(private val api: DragonBallAPI): RemoteDataSource {

    override suspend fun getToken(): String {
        return api.getToken()
    }

    override suspend fun getHeroes(): List<HeroResponse> {
        return api.getHeroes(HeroRequest())
    }
}


