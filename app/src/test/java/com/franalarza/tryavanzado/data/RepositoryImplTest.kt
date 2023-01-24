package com.franalarza.tryavanzado.data


import com.franalarza.tryavanzado.data.local.LocalDataSource
import com.franalarza.tryavanzado.data.mappers.Mappers
import com.franalarza.tryavanzado.data.remote.RemoteDataSource
import com.franalarza.tryavanzado.utils.generateHeroesResponse
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class RepositoryImplTest {

    private lateinit var sut: RepositoryImpl

    @Test
    fun `WHEN getHeroes THEN success not empty list`() = runTest {
        // GIVEN
        val localDataSource = mockk<LocalDataSource>()
        val remoteDataSource = mockk<RemoteDataSource>()

        coEvery { remoteDataSource.getHeroes() } returns generateHeroesResponse()
        sut = RepositoryImpl(remoteDataSource, localDataSource, Mappers())

        // WHEN
        val actualList = sut.getHeroes()

        // THEN
        coVerify { remoteDataSource.getHeroes() }
        Truth.assertThat(actualList).isNotEmpty()
        Truth.assertThat(actualList).containsExactlyElementsIn(generateHeroesResponse())
    }

}