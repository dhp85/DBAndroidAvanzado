package com.keepcoding.dbandroidavanzado.data.Network

import com.keepcoding.dbandroidavanzado.data.Network.model.GetHerosRequest
import com.keepcoding.dbandroidavanzado.domain.entities.HeroModelDto
import javax.inject.Inject

class NetworkDetail @Inject constructor(
    private val api: DetailApi) {

    suspend fun getHero(name: String): List<HeroModelDto> {
        return api.getHero(GetHerosRequest(name))

    }
}