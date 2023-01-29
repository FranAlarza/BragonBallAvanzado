package com.franalarza.tryavanzado.domain

import com.franalarza.tryavanzado.data.local.models.HeroLocal
import com.franalarza.tryavanzado.ui.herodetail.HeroDetailStatus
import com.franalarza.tryavanzado.ui.herodetail.HeroLocalState
import com.franalarza.tryavanzado.ui.herodetail.LocationState
import com.franalarza.tryavanzado.ui.herolist.HeroesState
import com.franalarza.tryavanzado.ui.login.LoginState

interface Repository {
    suspend fun getToken(email: String, password: String): LoginState
    suspend fun getHeroes(): List<HeroPresent>
    suspend fun getHeroesFromLocal(): HeroesState
    suspend fun saveHeroFromLocal(hero: HeroLocal)
    suspend fun getHeroFromLocal(name: String, id: String): HeroLocalState
    suspend fun getHeroesDetail(name: String): HeroDetailStatus
    suspend fun getLocations(id: String): LocationState
    suspend fun toogleFavorite(hero: String)
    suspend fun toggleFavoriteLocal(id: String)
}
