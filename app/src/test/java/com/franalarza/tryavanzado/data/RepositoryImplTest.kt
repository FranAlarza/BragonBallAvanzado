package com.franalarza.tryavanzado.data

import android.util.Log.e
import com.franalarza.tryavanzado.data.local.LocalDataSource
import com.franalarza.tryavanzado.data.mappers.Mappers
import com.franalarza.tryavanzado.data.remote.RemoteDataSource
import com.franalarza.tryavanzado.data.remote.response.HeroResponse
import com.franalarza.tryavanzado.domain.HeroPresent
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class RepositoryImplTest {

    private lateinit var sut: RepositoryImpl

    @Test
    fun `WHEN getHeroes THEN success not empty list`() = runTest {
        // GIVEN
        val localDataSource = mockk<LocalDataSource>()
        val remoteDataSource = mockk<RemoteDataSource>()

        coEvery { remoteDataSource.getHeroes() } returns generateHeroes()
        sut = RepositoryImpl(remoteDataSource, localDataSource, Mappers())

        // WHEN
        val actualList = sut.getHeroes()

        // THEN
        coVerify { remoteDataSource.getHeroes() }
        Truth.assertThat(actualList).isNotEmpty()
        Truth.assertThat(actualList).containsExactlyElementsIn(generateHeroes())
    }

    private fun generateHeroes(): List<HeroResponse> {
       return (0..10).map { HeroResponse("$it", "name $it", "photo $it", false, "description $it") }
    }
}