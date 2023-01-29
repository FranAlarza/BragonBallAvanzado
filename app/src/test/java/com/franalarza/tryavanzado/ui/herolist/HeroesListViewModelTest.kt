package com.franalarza.tryavanzado.ui.herolist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.franalarza.tryavanzado.domain.Repository
import com.franalarza.tryavanzado.ui.herodetail.HeroDetailStatus

import com.franalarza.tryavanzado.utils.generateHeroesPresent
import com.franalarza.tryavanzado.utils.getOrAwaitValue
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
@OptIn(ExperimentalCoroutinesApi::class)
class HeroesListViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var sut: HeroesListViewModel
    private lateinit var repository: Repository
    @OptIn(DelicateCoroutinesApi::class)
    private val mainThreadSurrogate = newSingleThreadContext("UI Thread")

    @Before
    fun setup() {
        Dispatchers.setMain(mainThreadSurrogate)
    }


    @Test
    fun `WHEN getHeroes THEN returns not empty list`() = runTest {
        // GIVEN
        repository = mockk()
        sut = HeroesListViewModel(repository)
        coEvery { repository.getHeroesFromLocal() } returns HeroesState.Success(
            generateHeroesPresent()
        )
        // WHEN
        sut.getHeroes()
        val actualLiveDataList = sut.liveHeroes.getOrAwaitValue()
        // THEN
        assert(actualLiveDataList is HeroesState.Success)
        Truth.assertThat((actualLiveDataList as HeroesState.Success).heroes).isEqualTo(
            generateHeroesPresent()
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }
}