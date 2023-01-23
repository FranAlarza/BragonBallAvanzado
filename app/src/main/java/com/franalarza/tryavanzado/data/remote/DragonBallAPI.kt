package com.franalarza.tryavanzado.data.remote

import com.franalarza.tryavanzado.data.remote.request.HeroRequest
import com.franalarza.tryavanzado.data.remote.response.HeroResponse
import com.franalarza.tryavanzado.domain.HeroPresent
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface DragonBallAPI {
    @GET("/api/auth/login")
    suspend fun getToken(): String

    @POST("/api/heros/all")
    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiIsImtpZCI6InByaXZhdGUiLCJ0eXAiOiJKV1QifQ.eyJpZGVudGlmeSI6IjAyNzE3QkY0LTNDMTgtNEU1NS05RjBFLUFDRkQxNUM1Q0FGNyIsImVtYWlsIjoiZnJhbmFsYXJ6YUBnbWFpbC5jb20iLCJleHBpcmF0aW9uIjo2NDA5MjIxMTIwMH0.HgNvYLXRlEGXYtKXw1lgyQmHD1tltpRzSptGnis2fsA")
    suspend fun getHeroes(@Body heroRequest: HeroRequest): List<HeroResponse>
}