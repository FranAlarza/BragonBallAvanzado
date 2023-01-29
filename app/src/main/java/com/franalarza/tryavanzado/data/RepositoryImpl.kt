package com.franalarza.tryavanzado.data

import android.util.Log
import com.franalarza.tryavanzado.ui.herolist.HeroesState
import com.franalarza.tryavanzado.data.local.LocalDataSource
import com.franalarza.tryavanzado.data.local.models.HeroLocal
import com.franalarza.tryavanzado.data.mappers.Mappers
import com.franalarza.tryavanzado.data.remote.RemoteDataSource
import com.franalarza.tryavanzado.domain.HeroDetail
import com.franalarza.tryavanzado.domain.HeroDetailWithLocation
import com.franalarza.tryavanzado.domain.HeroPresent
import com.franalarza.tryavanzado.domain.Repository
import com.franalarza.tryavanzado.ui.herodetail.HeroDetailStatus
import com.franalarza.tryavanzado.ui.herodetail.HeroLocalState
import com.franalarza.tryavanzado.ui.herodetail.LocationState
import com.franalarza.tryavanzado.ui.login.LoginState
import javax.inject.Inject
import kotlinx.coroutines.*
import retrofit2.HttpException

class RepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val mappers: Mappers
) : Repository {

    override suspend fun getToken(email: String, password: String): LoginState {
        val result = remoteDataSource.getToken(email, password)
        return when {
            result.isSuccess -> {
                LoginState.Success(result.getOrThrow())

            }
            else -> {
                LoginState.Failure("No es posibole recuperar el token")
            }
        }
    }

    override suspend fun getHeroes(): List<HeroPresent> {
        return mappers.mapRemoteToPresentation(remoteDataSource.getHeroes())
    }

    override suspend fun getHeroesFromLocal(): HeroesState {
        val heroesFromLocal = localDataSource.getHeroes()


        return if (heroesFromLocal.isEmpty()) {
            localDataSource.saveHeroesInLocal(mappers.mapRemoteToLocal(remoteDataSource.getHeroes()))
            HeroesState.Success(mappers.mapRemoteToPresentation(remoteDataSource.getHeroes()))
        } else {
            HeroesState.Success(
                mappers.mapLocalToPresentation(
                    localDataSource.getHeroes()
                )
            )
        }
    }

    override suspend fun saveHeroFromLocal(hero: HeroLocal) {
        localDataSource.saveHeroInLocal(hero)
    }

    override suspend fun getHeroFromLocal(name: String, id: String): HeroLocalState {
        val result = localDataSource.getHeroFromLocal(id)

        return when {
            result.isSuccess -> {
                if (result.getOrNull() != null) {
                    HeroLocalState.Success(result.getOrThrow())
                } else {
                    HeroLocalState.Success(
                        mappers.mapDetailToLocal(
                            remoteDataSource.getHeroDetail(name).getOrThrow()
                        )
                    )
                }
            }
            else -> {
                HeroLocalState.Failure("Error Al cargar de local")
            }
        }
    }

    override suspend fun getHeroesDetail(name: String): HeroDetailStatus {
        val result = remoteDataSource.getHeroDetail(name)
        return when {
            result.isSuccess -> {
                HeroDetailStatus.Success(result.getOrThrow())
            }
            else -> {
                HeroDetailStatus.Failure("No hemos podido cargar el detalle")
            }
        }
    }

    override suspend fun getLocations(id: String)
            : LocationState {
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
                LocationState.Failure("No hay localizaciones disponibles para este heroe")
            }
        }
    }

    override suspend fun toogleFavorite(hero: String) {
        remoteDataSource.toogleFavorite(hero)
    }

    override suspend fun toggleFavoriteLocal(id: String) {
        localDataSource.toggleFavoriteLocal(id)
    }


}