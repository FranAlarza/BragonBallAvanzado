package com.franalarza.tryavanzado.ui.herodetail

import com.franalarza.tryavanzado.domain.HeroDetail
import com.franalarza.tryavanzado.domain.HeroDetailWithLocation

sealed class HeroDetailStatus {
    data class Success(val heroesDetail: HeroDetail): HeroDetailStatus()
    data class Failure(val errorMessage: String): HeroDetailStatus()
}