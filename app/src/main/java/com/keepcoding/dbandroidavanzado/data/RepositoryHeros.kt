package com.keepcoding.dbandroidavanzado.data

import com.keepcoding.dbandroidavanzado.entities.HeroModelDto

class RepositoryHeros {

    suspend fun getHeros(): List<HeroModelDto> {
        return NetworkHeros().getHeros()

    }


}