package com.keepcoding.dbandroidavanzado.entities

import com.squareup.moshi.Json

data class HeroModelDto(
  @Json (name = "id")val id: String,
  @Json (name = "name")val name: String,
  @Json (name = "photo")val photo: String,
  @Json (name = "favorite")val favorite: Boolean,
  @Json (name = "description")val description: String
)

