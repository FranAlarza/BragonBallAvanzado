package com.franalarza.tryavanzado.ui.herodetail

import android.location.Location
import com.franalarza.tryavanzado.domain.HeroLocation

sealed class LocationState {
    data class Success(val locations: HeroLocation): LocationState()
    data class Failure(val errorMessage: String): LocationState()
}
