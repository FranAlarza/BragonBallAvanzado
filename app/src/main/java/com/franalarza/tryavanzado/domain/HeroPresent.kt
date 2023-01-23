package com.franalarza.tryavanzado.domain

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class HeroPresent(
    @Json(name = "id")val id: String,
    @Json(name = "name")val name: String,
    @Json(name = "photo")val photo: String,
    @Json(name = "favorite")val favorite: Boolean) : Parcelable
