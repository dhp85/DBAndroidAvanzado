package com.keepcoding.dbandroidavanzado.data.Network.base

import com.keepcoding.dbandroidavanzado.data.Network.model.GetLocationsRequest
import com.keepcoding.dbandroidavanzado.data.Network.networkapi.LocationApi
import com.keepcoding.dbandroidavanzado.domain.entities.LocationModelDto
import javax.inject.Inject

class NetworkLocations @Inject constructor(private val api: LocationApi) {

    suspend fun getLocations(id: String): Result<List<LocationModelDto>> = runCatching {
        api.getLocations(GetLocationsRequest(id))
    }
}