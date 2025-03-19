package com.keepcoding.dbandroidavanzado.domain.entities

data class LocationModelDto(
    val dateShow: String,
    val id: String,
    val hero: Hero,
    val longitud: String,
    val latitud: String
){
    fun toLocationModel(): LocationModel {
        return LocationModel(
            longitud = longitud,
            latitud = latitud
        )

    }
}

data class Hero (
    val id: String
)

