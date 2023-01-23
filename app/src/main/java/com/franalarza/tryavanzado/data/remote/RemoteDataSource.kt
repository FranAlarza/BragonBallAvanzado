package com.franalarza.tryavanzado.data.remote

import com.franalarza.tryavanzado.data.remote.request.HeroRequest
import com.franalarza.tryavanzado.data.remote.response.HeroResponse

interface RemoteDataSource {
    suspend fun getToken(): String
    suspend fun getHeroes(): List<HeroResponse>
}