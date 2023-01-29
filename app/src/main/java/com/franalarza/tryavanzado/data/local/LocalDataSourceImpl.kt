package com.franalarza.tryavanzado.data.local

import com.franalarza.tryavanzado.data.local.models.HeroLocal
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val dao: HeroDAO): LocalDataSource {

    override fun getHeroes(): List<HeroLocal> {
        return dao.getAllHeroes()
    }

    override fun saveHeroesInLocal(heroes: List<HeroLocal>) {
        dao.insertAll(heroes)
    }

    override fun saveHeroInLocal(hero: HeroLocal) {
        dao.insertHero(hero)
    }

    override fun getHeroFromLocal(id: String): Result<HeroLocal> {
        return runCatching { dao.getHeroFromLocal(id) }
    }

    override fun toggleFavoriteLocal(id: String) {
        return dao.toggleFavoriteInLocal(id)
    }

}


