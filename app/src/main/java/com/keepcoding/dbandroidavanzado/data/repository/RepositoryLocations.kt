package com.keepcoding.dbandroidavanzado.data.repository

import android.util.Log
import com.keepcoding.dbandroidavanzado.data.Network.base.NetworkLocations
import com.keepcoding.dbandroidavanzado.domain.entities.LocationModelDto
import javax.inject.Inject

class RepositoryLocations @Inject constructor(
    private val networkLocations: NetworkLocations) {

    suspend fun getLocations(id: String): Result<List<LocationModelDto>> = runCatching {

        networkLocations.getLocations(id).getOrElse {
            Result.failure<List<LocationModelDto>>(it)
            Log.e("RepositoryLocations", "Error getting locations from network", it)
            throw it
        }
    }
}