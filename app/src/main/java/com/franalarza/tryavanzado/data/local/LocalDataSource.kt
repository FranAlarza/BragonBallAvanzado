package com.franalarza.tryavanzado.data.local

import com.franalarza.tryavanzado.data.local.models.HeroLocal

interface LocalDataSource {
    fun getHeroes(): Result<List<HeroLocal>>
    fun saveHeroesInLocal(heroes: List<HeroLocal>)
}