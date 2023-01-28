package com.franalarza.tryavanzado.ui.herolist

import com.franalarza.tryavanzado.domain.HeroPresent

sealed class HeroesState {
    data class Success(val heroes: List<HeroPresent>): HeroesState()
    data class Failure(val errorMessage: String): HeroesState()
}
