package com.keepcoding.dbandroidavanzado.data

import com.keepcoding.dbandroidavanzado.data.model.GetHerosRequest
import com.keepcoding.dbandroidavanzado.entities.HeroModelDto
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST


interface HerosApi {

    @POST("api/heros/all")
    @Headers("Authorization: Bearer eyJraWQiOiJwcml2YXRlIiwiYWxnIjoiSFMyNTYiLCJ0eXAiOiJKV1QifQ.eyJlbWFpbCI6ImRpZWdvaHA4NUBnbWFpbC5jb20iLCJleHBpcmF0aW9uIjo2NDA5MjIxMTIwMCwiaWRlbnRpZnkiOiJGQkY5QkUzRi02QzgwLTQ2MUItOUFFMC1DMDUxOUI0OEYxRjcifQ.fllqJ0Q0KL3yQAppQwTR64KuFh_HyIZDLOXtvx8kGzM")
    suspend  fun getHeros(@Body request: GetHerosRequest): List<HeroModelDto>
}