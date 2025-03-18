package com.keepcoding.dbandroidavanzado.data.Network

import com.keepcoding.dbandroidavanzado.data.Network.model.GetHerosRequest
import com.keepcoding.dbandroidavanzado.di.BearerClient
import com.keepcoding.dbandroidavanzado.domain.entities.HeroModelDto
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

class NetworkHeros @Inject constructor(
    private val api: HerosApi) {


    suspend fun getHeros(): List<HeroModelDto> {
        return api.getHeros(GetHerosRequest()).sortedBy { it.name }

    }
}