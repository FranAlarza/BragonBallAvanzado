package com.franalarza.tryavanzado.data.remote

import android.content.Context
import android.content.SharedPreferences
import android.location.Location
import com.franalarza.tryavanzado.data.local.PreferencesManager
import com.franalarza.tryavanzado.data.remote.request.FavoriteRequest
import com.franalarza.tryavanzado.data.remote.request.HeroRequest
import com.franalarza.tryavanzado.data.remote.request.LocationsRequest
import com.franalarza.tryavanzado.data.remote.request.TokenRequest
import com.franalarza.tryavanzado.data.remote.response.HeroResponse
import com.franalarza.tryavanzado.domain.HeroDetail
import com.franalarza.tryavanzado.domain.HeroLocation
import com.franalarza.tryavanzado.ui.herolist.HeroesState
import com.franalarza.tryavanzado.ui.login.LoginState
import okhttp3.Credentials
import javax.inject.Inject


class RemoteDataSourceImpl @Inject constructor(private val api: DragonBallAPI, private val sharedPreferences: PreferencesManager): RemoteDataSource {

    private val token = sharedPreferences.fetchAuthToken() ?: ""

    override suspend fun getToken(user: String, password: String): String {
        return api.getToken(Credentials.basic(user, password))
    }

    override suspend fun getHeroes(): List<HeroResponse> {
        return api.getHeroes("Bearer $token", HeroRequest())
    }

    override suspend fun getHeroDetail(id: String): List<HeroDetail> {
        return api.getHeroesDetail("Bearer $token", HeroRequest(id))
    }

    override suspend fun getHeroLocation(id: String): Result<List<HeroLocation>> {
        return runCatching { api.getLocations("Bearer $token", LocationsRequest(id)) }
    }

    override suspend fun toogleFavorite(hero: String) {
        api.toogleFavoriteHero("Bearer $token", FavoriteRequest(hero))
    }
}


