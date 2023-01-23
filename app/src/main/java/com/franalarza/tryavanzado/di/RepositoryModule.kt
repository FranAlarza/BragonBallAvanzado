package com.franalarza.tryavanzado.di

import com.franalarza.tryavanzado.domain.Repository
import com.franalarza.tryavanzado.data.RepositoryImpl
import com.franalarza.tryavanzado.data.local.LocalDataSource
import com.franalarza.tryavanzado.data.local.LocalDataSourceImpl
import com.franalarza.tryavanzado.data.remote.RemoteDataSource
import com.franalarza.tryavanzado.data.remote.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindRepository(repositoryImpl: RepositoryImpl): Repository

    @Binds
    abstract fun bindRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource

    @Binds
    abstract fun bindLocalDataSource(localDataSourceImpl: LocalDataSourceImpl): LocalDataSource

}