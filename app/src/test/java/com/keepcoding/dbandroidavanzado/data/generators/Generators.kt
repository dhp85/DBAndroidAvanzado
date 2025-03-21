package com.keepcoding.dbandroidavanzado.data.generators

import com.keepcoding.dbandroidavanzado.data.local.HeroModelLocal
import com.keepcoding.dbandroidavanzado.domain.entities.HeroModelDto

fun getHerosLocal(size: Int = 10): List<HeroModelLocal> = List(size) {
    HeroModelLocal(
        id = "id$it",
        name = "name $it",
        description = "description $it",
        photo = "photo$it",
        favorite = false,
    )
}

fun getHerosMock(size: Int = 10): List<HeroModelDto> = List(size){
    HeroModelDto(
        id = "id$it",
        name = "name $it",
        photo = "photo$it",
        favorite = false,
        description = "description $it",
    )
}
