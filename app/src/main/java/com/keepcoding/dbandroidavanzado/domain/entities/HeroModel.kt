package com.keepcoding.dbandroidavanzado.domain.entities

data class HeroModel (
    val id: String,
    val name: String,
    val photo: String,
    val favorite: Boolean,
    val description: String
)
