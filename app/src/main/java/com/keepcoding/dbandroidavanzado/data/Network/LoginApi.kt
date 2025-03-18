package com.keepcoding.dbandroidavanzado.data.Network


import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginApi {

    @POST("api/auth/login")
    suspend  fun login(): String

}