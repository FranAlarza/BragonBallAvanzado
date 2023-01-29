package com.franalarza.tryavanzado.fakes

import com.franalarza.tryavanzado.data.local.LocalDataSource
import com.franalarza.tryavanzado.data.local.models.HeroLocal
import com.franalarza.tryavanzado.utils.generateHeroesLocal

class LocalDataSourceFake: LocalDataSource {
    private var firstCall = true
    override fun getHeroes(): List<HeroLocal> {
        return if (firstCall){
            firstCall = false
            emptyList()
        } else {
            generateHeroesLocal()
        }
    }

    override fun saveHeroesInLocal(heroes: List<HeroLocal>) {
        TODO("Not yet implemented")
    }

    override fun saveHeroInLocal(hero: HeroLocal) {
        TODO("Not yet implemented")
    }

    override fun getHeroFromLocal(id: String): Result<HeroLocal> {
        TODO("Not yet implemented")
    }

    override fun toggleFavoriteLocal(id: String) {
        TODO("Not yet implemented")
    }


}