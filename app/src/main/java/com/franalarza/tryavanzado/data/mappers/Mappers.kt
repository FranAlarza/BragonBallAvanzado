package com.franalarza.tryavanzado.data.mappers

import com.franalarza.tryavanzado.data.local.models.HeroLocal
import com.franalarza.tryavanzado.data.remote.response.HeroResponse
import com.franalarza.tryavanzado.domain.HeroPresent
import javax.inject.Inject

class Mappers @Inject constructor() {

    fun mapRemoteToPresentation(heroes: List<HeroResponse>): List<HeroPresent> {
        return heroes.map { HeroPresent(it.id, it.name, it.photo, it.favorite) }
    }

    fun mapRemoteToLocal(heroes: List<HeroResponse>): List<HeroLocal> {
        return heroes.map { HeroLocal(it.id, it.name, it.photo,it.description, it.favorite) }
    }

    fun mapLocalToPresentation(heroes: List<HeroLocal>): List<HeroPresent> {
        return heroes.map { HeroPresent(it.id, it.name, it.photo, it.favorite) }
    }

}
