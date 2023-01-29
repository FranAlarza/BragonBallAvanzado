package com.franalarza.tryavanzado.data.remote

import com.franalarza.tryavanzado.data.local.PreferencesManager
import com.franalarza.tryavanzado.data.remote.request.FavoriteRequest
import com.franalarza.tryavanzado.data.remote.request.HeroRequest
import com.franalarza.tryavanzado.data.remote.request.LocationsRequest
import com.franalarza.tryavanzado.data.remote.response.HeroResponse
import com.franalarza.tryavanzado.domain.HeroDetail
import com.franalarza.tryavanzado.domain.HeroLocation
import okhttp3.Credentials
import javax.inject.Inject


class RemoteDataSourceImpl @Inject constructor(private val api: DragonBallAPI, private val sharedPreferences: PreferencesManager): RemoteDataSource {

    private val token = sharedPreferences.fetchAuthToken() ?: ""

    override suspend fun getToken(user: String, password: String): Result<String> {
        return runCatching {api.getToken(Credentials.basic(user, password))}
    }

    override suspend fun getHeroes(): List<HeroResponse> {
        return api.getHeroes("Bearer $token", HeroRequest())
    }

    override suspend fun getHeroDetail(id: String): Result<HeroDetail> {
        return runCatching { api.getHeroesDetail("Bearer $token", HeroRequest(id)).first() }
    }

    override suspend fun getHeroLocation(id: String): Result<List<HeroLocation>> {
        return runCatching { api.getLocations("Bearer $token", LocationsRequest(id)) }
    }

    override suspend fun toogleFavorite(hero: String) {
        api.toogleFavoriteHero("Bearer $token", FavoriteRequest(hero))
    }
}


