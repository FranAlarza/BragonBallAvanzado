package com.franalarza.tryavanzado.data.remote.response

import com.squareup.moshi.Json

data class HeroResponse(
    @Json(name = "id") val id: String,
    @Json(name = "name") val name: String,
    @Json(name = "photo") val photo: String,
    @Json(name = "favorite") val favorite: Boolean,
    @Json(name = "description") val description: String
)
