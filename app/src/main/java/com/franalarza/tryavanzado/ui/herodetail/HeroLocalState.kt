package com.franalarza.tryavanzado.ui.herodetail

import com.franalarza.tryavanzado.data.local.models.HeroLocal

sealed class HeroLocalState {
    data class Success(val hero: HeroLocal): HeroLocalState()
    data class Failure(val error: String): HeroLocalState()
}
