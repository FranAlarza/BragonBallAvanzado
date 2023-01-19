package com.franalarza.tryavanzado.data.remote

import retrofit2.Retrofit


class RemoteDataSource {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://dragonball.keepcoding.education")
        .build()

    private val api: DragonBallAPI = retrofit.create(DragonBallAPI::class.java)

    suspend fun getToken() {
        api.getToken()
    }
}