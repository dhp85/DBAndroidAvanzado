package com.keepcoding.dbandroidavanzado.data.Network.networkapi

import com.keepcoding.dbandroidavanzado.data.Network.model.GetHerosRequest
import com.keepcoding.dbandroidavanzado.domain.entities.HeroModelDto
import retrofit2.http.Body
import retrofit2.http.POST

interface DetailApi {

    @POST("api/heros/all")
    suspend  fun getHero(@Body request: GetHerosRequest): List<HeroModelDto>

}