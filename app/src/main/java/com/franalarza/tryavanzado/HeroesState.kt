package com.franalarza.tryavanzado

import com.franalarza.tryavanzado.domain.HeroPresent

sealed class HeroesState {
    data class Success(val heroes: List<HeroPresent>): HeroesState()
    data class Failure(val heroes: List<HeroPresent>): HeroesState()
}
