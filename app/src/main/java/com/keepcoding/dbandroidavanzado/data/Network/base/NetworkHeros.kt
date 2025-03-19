package com.keepcoding.dbandroidavanzado.data.Network.base

import com.keepcoding.dbandroidavanzado.data.Network.model.GetHerosRequest
import com.keepcoding.dbandroidavanzado.data.Network.networkapi.HerosApi
import com.keepcoding.dbandroidavanzado.domain.entities.HeroModelDto
import javax.inject.Inject

class NetworkHeros @Inject constructor(
    private val api: HerosApi
) {


    suspend fun getHeros(): List<HeroModelDto> {
        return api.getHeros(GetHerosRequest()).sortedBy { it.name }

    }
}