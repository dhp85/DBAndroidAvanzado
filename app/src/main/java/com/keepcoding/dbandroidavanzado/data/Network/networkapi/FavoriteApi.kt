package com.keepcoding.dbandroidavanzado.data.Network.networkapi

import com.keepcoding.dbandroidavanzado.data.Network.model.GetFavoriteRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface FavoriteApi {

    @POST("api/data/herolike")
    suspend fun getFavorite(@Body request: GetFavoriteRequest)
}