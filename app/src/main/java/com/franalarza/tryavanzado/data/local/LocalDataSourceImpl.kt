package com.franalarza.tryavanzado.data.local

import com.franalarza.tryavanzado.data.local.models.HeroLocal
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val dao: HeroDAO): LocalDataSource {

    override fun getHeroes(): Result<List<HeroLocal>> {
        return kotlin.runCatching { dao.getAllHeroes() }
    }

    override fun saveHeroesInLocal(heroes: List<HeroLocal>) {
        dao.insertAll(heroes)
    }

}


