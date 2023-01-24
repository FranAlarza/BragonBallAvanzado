package com.franalarza.tryavanzado.utils

import com.franalarza.tryavanzado.data.remote.response.HeroResponse
import com.franalarza.tryavanzado.domain.HeroPresent

fun generateHeroesResponse(): List<HeroResponse> {
    return (0..5).map { HeroResponse("$it", "name $it", "photo $it", false, "description $it") }
}

fun generateHeroesPresent(): List<HeroPresent> {
    return (0..5).map { HeroPresent("$it", "name $it", "photo $it", false) }
}