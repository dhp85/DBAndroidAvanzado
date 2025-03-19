package com.keepcoding.dbandroidavanzado.data.Network.networkapi


import com.keepcoding.dbandroidavanzado.data.Network.model.GetLocationsRequest
import com.keepcoding.dbandroidavanzado.domain.entities.LocationModelDto
import retrofit2.http.Body
import retrofit2.http.POST

interface LocationApi {
    @POST("api/heros/locations")
    suspend fun getLocations(@Body request: GetLocationsRequest): List<LocationModelDto>

}