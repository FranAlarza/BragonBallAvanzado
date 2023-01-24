package com.franalarza.tryavanzado.ui.herolist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.franalarza.tryavanzado.domain.Repository

import com.franalarza.tryavanzado.utils.generateHeroesPresent
import com.franalarza.tryavanzado.utils.getOrAwaitValue
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HeroesListViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var sut: HeroesListViewModel
    private lateinit var repository: Repository
    private val mainThreadSurrogate = newSingleThreadContext("UI Thread")

    @Before
    fun setup() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @Test
    fun getLiveHeroes() {


    }

    @Test
    fun getHeroes() = runTest {
        // GIVEN
        sut = HeroesListViewModel(repository)
        repository = mockk()
        coEvery { repository.getHeroes() } returns generateHeroesPresent()
        // WHEN
        sut.getHeroes()
        val actualLiveDataList = sut.liveHeroes.getOrAwaitValue()
        // THEN
        Truth.assertThat(sut.liveHeroes.value)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }
}