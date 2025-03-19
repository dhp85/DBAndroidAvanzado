package com.keepcoding.dbandroidavanzado.domain.entities

import com.squareup.moshi.Json

data class LocationModelDto(
    @Json(name = "dateShow")val dateShow: String,
    @Json(name = "id")val id: String,
    @Json(name = "hero")val hero: Hero,
    @Json(name = "longitud")val longitud: String,
    @Json(name = "latitud")val latitud: String
){
    fun toLocationModel(): LocationModel {
        return LocationModel(
            longitud = longitud,
            latitud = latitud
        )

    }
}

data class Hero (
   @Json(name = "id") val id: String
)

