package com.franalarza.tryavanzado.domain

data class HeroDetailWithLocation(
    val id: String,
    val name: String,
    val description: String,
    val favourite: Boolean,
    val latitud: String,
    val longitud: String
)