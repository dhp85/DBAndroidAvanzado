package com.keepcoding.dbandroidavanzado.data.Network.base

import com.keepcoding.dbandroidavanzado.data.Network.model.GetHerosRequest
import com.keepcoding.dbandroidavanzado.data.Network.networkapi.DetailApi
import com.keepcoding.dbandroidavanzado.domain.entities.HeroModelDto
import javax.inject.Inject

class NetworkDetail @Inject constructor(
    private val api: DetailApi
) {

    suspend fun getHero(name: String): Result<List<HeroModelDto>> = runCatching{
        api.getHero(GetHerosRequest(name))
    }
}