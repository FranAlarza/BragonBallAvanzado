package com.franalarza.tryavanzado.data.remote

import com.franalarza.tryavanzado.domain.Bootcamps
import retrofit2.http.GET

interface DragonBallAPI {
    @GET("/api/auth/login")
    suspend fun getToken(): String
}