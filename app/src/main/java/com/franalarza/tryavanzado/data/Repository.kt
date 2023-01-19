package com.franalarza.tryavanzado.data

import com.franalarza.tryavanzado.data.remote.RemoteDataSource

class Repository {

    private val remoteDataSource = RemoteDataSource()

    suspend fun getToken() {
        remoteDataSource.getToken()
    }
}