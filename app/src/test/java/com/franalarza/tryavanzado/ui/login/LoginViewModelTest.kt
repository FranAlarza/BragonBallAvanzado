package com.franalarza.tryavanzado.ui.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.franalarza.tryavanzado.domain.Repository
import com.franalarza.tryavanzado.ui.herolist.HeroesListViewModel
import com.franalarza.tryavanzado.ui.herolist.HeroesState
import com.franalarza.tryavanzado.utils.generateHeroesPresent
import com.franalarza.tryavanzado.utils.generateToken
import com.franalarza.tryavanzado.utils.getOrAwaitValue
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LoginViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var sut: LoginViewModel
    private lateinit var repository: Repository

    @OptIn(DelicateCoroutinesApi::class)
    private val mainThreadSurrogate = newSingleThreadContext("UI Thread")

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `WHEN login THEN returns token succesfully`() {
        // GIVEN
        repository = mockk()
        sut = LoginViewModel(repository)
        coEvery { repository.getToken("fran@alarza.com", "123456") } returns
                generateToken()

        // WHEN
        sut.login("fran@alarza.com", "123456")
        val actualLive = sut.liveToken.getOrAwaitValue()

        // THEN

        assert(actualLive is LoginState.Success)
        Truth.assertThat((actualLive as LoginState.Success)).isEqualTo(
            generateToken()
        )

    }
}