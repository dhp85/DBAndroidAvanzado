package com.keepcoding.dbandroidavanzado.domain.entities

import com.keepcoding.dbandroidavanzado.data.local.HeroModelLocal
import com.squareup.moshi.Json



data class HeroModelDto(
  @Json (name = "id")val id: String,
  @Json (name = "name")val name: String,
  @Json (name = "photo")val photo: String,
  @Json (name = "favorite")val favorite: Boolean,
  @Json (name = "description")val description: String
) {
  fun toHeroModel(): HeroModel {
    return HeroModel(
      id = id,
      name = name,
      photo = photo,
      favorite = favorite,
      description = description
    )
  }

  fun toHeroModelLocal(): HeroModelLocal {
    return HeroModelLocal(
      id = id,
      name = name,
      photo = photo,
      favorite = favorite,
      description = description
    )
  }

}

