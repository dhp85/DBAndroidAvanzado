package com.keepcoding.dbandroidavanzado.data.Network.base

import com.keepcoding.dbandroidavanzado.data.Network.model.GetFavoriteRequest
import com.keepcoding.dbandroidavanzado.data.Network.networkapi.FavoriteApi
import javax.inject.Inject

class NetworkFavorite @Inject constructor(
    private val api: FavoriteApi
){
    suspend fun getFavorite(id: String) = runCatching {
        api.getFavorite(GetFavoriteRequest(id))
    }
}