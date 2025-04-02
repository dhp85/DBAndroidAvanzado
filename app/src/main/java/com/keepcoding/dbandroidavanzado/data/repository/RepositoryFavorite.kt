package com.keepcoding.dbandroidavanzado.data.repository

import android.util.Log
import com.keepcoding.dbandroidavanzado.data.Network.base.NetworkFavorite
import com.keepcoding.dbandroidavanzado.data.local.LocalDataSource
import javax.inject.Inject

class RepositoryFavorite @Inject constructor(
    private val networkFavorite: NetworkFavorite,
    private val localDataHeros: LocalDataSource) {



    suspend fun toggleFavorite(id: String) = runCatching {
       val toggle = networkFavorite.getFavorite(id).onSuccess { response ->
           Result.success(response)
           Log.d("RepositoryFavorite", "Favorite toggled successfully")
       }.onFailure { throwable ->
           Result.failure<Exception>(throwable)
           Log.e("RepositoryFavorite", "Error getting favorite from network", throwable)
       }
        if (toggle.isSuccess) {
           val currentFavoriteStatus = localDataHeros.getFavoriteStatus(id)
            if (currentFavoriteStatus) {
                localDataHeros.updateFavorite(id, false)
                Log.d("RepositoryFavorite", "Favorite local updated to false")
            } else {
                localDataHeros.updateFavorite(id, true)
                Log.d("RepositoryFavorite", "Favorite local updated to true")
            }
        }


    }

}