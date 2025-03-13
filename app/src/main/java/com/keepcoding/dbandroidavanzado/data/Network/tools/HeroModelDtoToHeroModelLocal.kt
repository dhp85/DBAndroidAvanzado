package com.keepcoding.dbandroidavanzado.data.Network.tools

import com.keepcoding.dbandroidavanzado.data.local.HeroModelLocal
import com.keepcoding.dbandroidavanzado.entities.HeroModelDto

class HeroModelDtoToHeroModelLocal {
    fun toLocasl(heroDto: HeroModelDto): HeroModelLocal {
        return HeroModelLocal(
            id = heroDto.id,
            name = heroDto.name,
            photo = heroDto.photo,
            description = heroDto.description,
            favorite = heroDto.favorite)
    }
}

fun HeroModelDto.toLocal(): HeroModelLocal = HeroModelLocal(
    id = this.id,
    name = this.name,
    photo = this.photo,
    description = this.description,
    favorite = this.favorite)