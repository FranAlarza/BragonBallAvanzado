package com.franalarza.tryavanzado.data.remote

import com.franalarza.tryavanzado.data.remote.response.HeroResponse
import com.franalarza.tryavanzado.domain.HeroDetail
import com.franalarza.tryavanzado.domain.HeroLocation

interface RemoteDataSource {
    suspend fun getToken(user: String, password: String): Result<String>
    suspend fun getHeroes(): List<HeroResponse>
    suspend fun getHeroDetail(id: String): Result<HeroDetail>
    suspend fun getHeroLocation(id: String): Result<List<HeroLocation>>
    suspend fun toogleFavorite(hero: String)
}