package com.keepcoding.dbandroidavanzado.data.Network

import com.keepcoding.dbandroidavanzado.data.Network.model.GetHerosRequest
import com.keepcoding.dbandroidavanzado.entities.HeroModelDto
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

class NetworkHeros @Inject constructor(
    private val api: HerosApi) {


    suspend fun getHeros(): List<HeroModelDto> {
        return api.getHeros(GetHerosRequest()).sortedBy { it.name }

    }
}