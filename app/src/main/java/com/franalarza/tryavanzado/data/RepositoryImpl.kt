package com.franalarza.tryavanzado.data

import com.franalarza.tryavanzado.HeroesState
import com.franalarza.tryavanzado.data.local.LocalDataSource
import com.franalarza.tryavanzado.data.mappers.Mappers
import com.franalarza.tryavanzado.data.remote.RemoteDataSource
import com.franalarza.tryavanzado.domain.HeroPresent
import com.franalarza.tryavanzado.domain.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val mappers: Mappers
) : Repository {

    override suspend fun getToken() {
        remoteDataSource.getToken()
    }

    override suspend fun getHeroes(): List<HeroPresent> {
        return mappers.mapRemoteToPresentation(remoteDataSource.getHeroes())
    }

    override suspend fun getHeroesFromLocal(): HeroesState {
        val heroesFromLocal = localDataSource.getHeroes()

        return when {
            heroesFromLocal.isSuccess -> {
                HeroesState.Success(
                    mappers.mapLocalToPresentation(
                        localDataSource.getHeroes().getOrThrow()
                    )
                )
            }
            else -> {
                HeroesState.Failure(mappers.mapRemoteToPresentation(remoteDataSource.getHeroes()))
            }
        }


    }
}