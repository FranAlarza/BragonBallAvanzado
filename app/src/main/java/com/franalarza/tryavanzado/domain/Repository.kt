package com.franalarza.tryavanzado.domain

import com.franalarza.tryavanzado.HeroesState
import com.franalarza.tryavanzado.domain.HeroPresent

interface Repository {
    suspend fun getToken()
    suspend fun getHeroes(): List<HeroPresent>
    suspend fun getHeroesFromLocal(): HeroesState
}
