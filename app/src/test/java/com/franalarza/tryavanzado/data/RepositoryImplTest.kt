package com.franalarza.tryavanzado.data


import com.franalarza.tryavanzado.data.local.LocalDataSource
import com.franalarza.tryavanzado.data.mappers.Mappers
import com.franalarza.tryavanzado.data.remote.RemoteDataSource
import com.franalarza.tryavanzado.fakes.FakeRemoteDataSouce
import com.franalarza.tryavanzado.fakes.LocalDataSourceFake
import com.franalarza.tryavanzado.ui.herodetail.HeroDetailStatus
import com.franalarza.tryavanzado.ui.login.LoginState
import com.franalarza.tryavanzado.utils.generateHeroesPresent
import com.franalarza.tryavanzado.utils.generateHeroesResponse
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class RepositoryImplTest {

    private lateinit var sut: RepositoryImpl
    private val fakeRemoteDataSource = FakeRemoteDataSouce()
    private val fakeLocalDataSource = LocalDataSourceFake()

    @Before
    fun setUpMocks(){
        sut = RepositoryImpl(
            fakeRemoteDataSource,
            fakeLocalDataSource,
            Mappers()
        )
    }

    @Test
    fun `WHEN getToken THEN success not empty token`() = runTest {

        // WHEN
        val actualToken = sut.getToken("franalarza@gmail.com", "123456")

        // THEN
        assert(actualToken is LoginState.Success)
        Truth.assertThat((actualToken as LoginState.Success).token).isEqualTo("token")
    }

    @Test
    fun `WHEN getHeroes THEN success not empty list`() = runTest {
        // GIVEN

        // WHEN
        val actualList = sut.getHeroes()

        // THEN
        Truth.assertThat(actualList).isNotEmpty()
        Truth.assertThat(actualList).containsExactlyElementsIn(generateHeroesPresent())
    }

    @Test
    fun `WHEN getHeroDetail THEN success not empty list`() = runTest {
        // GIVEN

        // WHEN
        val actual = sut.getHeroesDetail("SUCCESS")

        // THEN
        assert(actual is HeroDetailStatus.Success)
        Truth.assertThat((actual as HeroDetailStatus.Success).heroesDetail.name).isEqualTo("Goku")
    }

}