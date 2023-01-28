package com.franalarza.tryavanzado.data.remote

import android.location.Location
import com.franalarza.tryavanzado.data.remote.request.FavoriteRequest
import com.franalarza.tryavanzado.data.remote.request.HeroRequest
import com.franalarza.tryavanzado.data.remote.request.LocationsRequest
import com.franalarza.tryavanzado.data.remote.request.TokenRequest
import com.franalarza.tryavanzado.data.remote.response.HeroResponse
import com.franalarza.tryavanzado.domain.HeroDetail
import com.franalarza.tryavanzado.domain.HeroLocation
import com.franalarza.tryavanzado.domain.HeroPresent
import com.franalarza.tryavanzado.ui.login.LoginState
import retrofit2.http.*

interface DragonBallAPI {
    @POST("/api/auth/login")
    suspend fun getToken(@Header("Authorization") token: String): String

    @POST("/api/heros/all")
    suspend fun getHeroes(@Header("Authorization") token: String ,@Body heroRequest: HeroRequest): List<HeroResponse>

    @POST("/api/heros/all")
    suspend fun getHeroesDetail(@Header("Authorization") token: String ,@Body heroRequest: HeroRequest): List<HeroDetail>

    @POST("/api/heros/locations")
    suspend fun getLocations(@Header("Authorization") token: String ,@Body locationRequest: LocationsRequest): List<HeroLocation>

    @POST("/api/data/herolike")
    suspend fun toogleFavoriteHero(@Header("Authorization") token: String, @Body favoriteRequest: FavoriteRequest)
}