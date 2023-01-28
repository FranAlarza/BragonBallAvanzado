package com.franalarza.tryavanzado.data.remote

import android.location.Location
import com.franalarza.tryavanzado.data.remote.request.HeroRequest
import com.franalarza.tryavanzado.data.remote.response.HeroResponse
import com.franalarza.tryavanzado.domain.HeroDetail
import com.franalarza.tryavanzado.domain.HeroLocation
import com.franalarza.tryavanzado.ui.herolist.HeroesState
import com.franalarza.tryavanzado.ui.login.LoginState

interface RemoteDataSource {
    suspend fun getToken(user: String, password: String): String
    suspend fun getHeroes(): List<HeroResponse>
    suspend fun getHeroDetail(id: String): List<HeroDetail>
    suspend fun getHeroLocation(id: String): Result<List<HeroLocation>>
    suspend fun toogleFavorite(hero: String)
}