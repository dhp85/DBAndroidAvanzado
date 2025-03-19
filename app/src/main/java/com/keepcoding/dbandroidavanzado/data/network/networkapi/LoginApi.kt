package com.keepcoding.dbandroidavanzado.data.Network.networkapi


import retrofit2.http.POST

interface LoginApi {

    @POST("api/auth/login")
    suspend  fun login(): String

}