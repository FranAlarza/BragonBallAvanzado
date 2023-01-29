package com.franalarza.tryavanzado.utils

import com.franalarza.tryavanzado.data.local.models.HeroLocal
import com.franalarza.tryavanzado.data.remote.response.HeroResponse
import com.franalarza.tryavanzado.domain.HeroDetail
import com.franalarza.tryavanzado.domain.HeroPresent
import com.franalarza.tryavanzado.ui.login.LoginState

fun generateHeroesResponse(): List<HeroResponse> {
    return (0..5).map { HeroResponse("$it", "name $it", "photo $it", false, "description $it") }
}

fun generateHeroesPresent(): List<HeroPresent> {
    return (0..5).map { HeroPresent("$it", "name $it", "photo $it", false) }
}

fun generateHeroesLocal(): List<HeroLocal> {
    return (0..5).map { HeroLocal("$it", "name $it", "photo $it", "description-1", false) }
}

fun generateHeroesDetail(): List<HeroDetail> {
    return (0..1).map { HeroDetail("$it", "name $it", "photo $it", "description-1", false) }
}

fun generateToken(): LoginState {
    return LoginState.Success("token")
}