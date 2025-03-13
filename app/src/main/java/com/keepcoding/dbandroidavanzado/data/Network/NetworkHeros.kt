package com.keepcoding.dbandroidavanzado.data.Network

import com.keepcoding.dbandroidavanzado.data.Network.model.GetHerosRequest
import com.keepcoding.dbandroidavanzado.entities.HeroModelDto
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class NetworkHeros {

companion object{
    private const val BASE_URL = "https://dragonball.keepcoding.education/"
}

    private val moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    private val  api = retrofit.create(HerosApi::class.java)

    suspend fun getHeros(): List<HeroModelDto> {
        return api.getHeros(GetHerosRequest()).sortedBy { it.name }

    }
}