package com.keepcoding.dbandroidavanzado.data.repository

import android.util.Log
import com.keepcoding.dbandroidavanzado.data.Network.base.NetworkLocations
import com.keepcoding.dbandroidavanzado.domain.entities.LocationModelDto
import javax.inject.Inject

class RepositoryLocations @Inject constructor(
    private val networkLocations: NetworkLocations) {
    suspend fun getLocations(id: String): List<LocationModelDto>{
        val remoteLocations = networkLocations.getLocations(id)

        Log.d("RepositoryLocations", "remoteLocations: $remoteLocations")
        return remoteLocations

    }
}