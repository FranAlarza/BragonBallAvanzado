package com.franalarza.tryavanzado.data

import android.util.Log
import com.franalarza.tryavanzado.ui.herolist.HeroesState
import com.franalarza.tryavanzado.data.local.LocalDataSource
import com.franalarza.tryavanzado.data.local.models.HeroLocal
import com.franalarza.tryavanzado.data.mappers.Mappers
import com.franalarza.tryavanzado.data.remote.RemoteDataSource
import com.franalarza.tryavanzado.domain.HeroDetailWithLocation
import com.franalarza.tryavanzado.domain.HeroPresent
import com.franalarza.tryavanzado.domain.Repository
import com.franalarza.tryavanzado.ui.herodetail.HeroDetailStatus
import com.franalarza.tryavanzado.ui.herodetail.LocationState
import javax.inject.Inject
import kotlinx.coroutines.*

class RepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val mappers: Mappers
) : Repository {

    override suspend fun getToken(email: String, password: String): String {
        return remoteDataSource.getToken(email, password)
    }

    override suspend fun getHeroes(): List<HeroPresent> {
        return mappers.mapRemoteToPresentation(remoteDataSource.getHeroes())
    }

    override suspend fun getHeroesFromLocal(): HeroesState {
        val heroesFromLocal = localDataSource.getHeroes()

        return when {
            heroesFromLocal.isSuccess -> {
                if (heroesFromLocal.getOrNull()?.isEmpty() == true) {
                    localDataSource.saveHeroesInLocal(mappers.mapRemoteToLocal(remoteDataSource.getHeroes()))
                    HeroesState.Success(mappers.mapRemoteToPresentation(remoteDataSource.getHeroes()))
                } else {
                    HeroesState.Success(
                        mappers.mapLocalToPresentation(
                            localDataSource.getHeroes().getOrThrow()
                        )
                    )
                }
            }
            else -> {
                HeroesState.Failure("No hemos podido cargar la lista de heroes")
            }
        }


    }

    override suspend fun getHeroFromLocal(hero: HeroLocal) {
        localDataSource.saveHeroInLocal(hero)
    }

    override suspend fun getHeroesDetail(name: String): HeroDetailStatus {
        val heroDetail = remoteDataSource.getHeroDetail(name)
        return if (heroDetail.isNotEmpty()) {
            HeroDetailStatus.Success(heroDetail.first())
        } else {
            HeroDetailStatus.Failure("No hemos podido cargar el detalle")
        }
    }

    override suspend fun getLocations(id: String): LocationState {
        val result = remoteDataSource.getHeroLocation(id)

        return when {
            result.isSuccess -> {
                if (result.getOrThrow().isNotEmpty()) {
                    LocationState.Success(result.getOrThrow().first())
                } else {
                    LocationState.Failure("No hay localizaciones disponibles")
                }
            }
            else -> {
                LocationState.Failure("Error en Localizaciones")
            }
        }
    }

    override suspend fun toogleFavorite(hero: String) {
        remoteDataSource.toogleFavorite(hero)
    }


}