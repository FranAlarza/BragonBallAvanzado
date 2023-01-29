package com.franalarza.tryavanzado.data.local

import com.franalarza.tryavanzado.data.local.models.HeroLocal

interface LocalDataSource {
    fun getHeroes(): List<HeroLocal>
    fun saveHeroesInLocal(heroes: List<HeroLocal>)
    fun saveHeroInLocal(hero: HeroLocal)
    fun getHeroFromLocal(id: String): Result<HeroLocal>
    fun toggleFavoriteLocal(id: String)
}