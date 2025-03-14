package com.keepcoding.dbandroidavanzado.data.Network

import com.keepcoding.dbandroidavanzado.data.Network.model.GetHerosRequest
import com.keepcoding.dbandroidavanzado.entities.HeroModelDto
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST


interface HerosApi {

    @POST("api/heros/all")
    suspend  fun getHeros(@Body request: GetHerosRequest): List<HeroModelDto>
}