package com.franalarza.tryavanzado.fakes

import com.franalarza.tryavanzado.data.remote.RemoteDataSource
import com.franalarza.tryavanzado.data.remote.response.HeroResponse
import com.franalarza.tryavanzado.domain.HeroDetail
import com.franalarza.tryavanzado.domain.HeroLocation
import com.franalarza.tryavanzado.ui.herodetail.HeroDetailStatus
import com.franalarza.tryavanzado.utils.generateHeroesDetail
import com.franalarza.tryavanzado.utils.generateHeroesResponse
import retrofit2.HttpException
import retrofit2.Response

class FakeRemoteDataSouce: RemoteDataSource {
    override suspend fun getToken(user: String, password: String): Result<String> {
        return if (user.isEmpty() || password.isEmpty()) {
            Result.failure(java.lang.NullPointerException("Datos Incorrectos"))
        } else {
            Result.success("token")
        }

    }

    override suspend fun getHeroes(): List<HeroResponse> {
        return generateHeroesResponse()
    }

    override suspend fun getHeroDetail(id: String): Result<HeroDetail> {
        return when(id){
            "SUCCESS" -> Result.success(HeroDetail("id", "Goku", "photo", "description", false))
            else -> {Result.failure(Exception())}
        }
    }

    override suspend fun getHeroLocation(id: String): Result<List<HeroLocation>> {
        TODO("Not yet implemented")
    }

    override suspend fun toogleFavorite(hero: String) {
        TODO("Not yet implemented")
    }
}