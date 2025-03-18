package com.keepcoding.dbandroidavanzado.data.repository

import com.keepcoding.dbandroidavanzado.data.Network.NetworkLogin
import com.keepcoding.dbandroidavanzado.data.Network.model.CredentialsProvider
import javax.inject.Inject

class RepositoryLogin @Inject constructor(
     private val networkLogin: NetworkLogin
) {

    suspend fun login(user: String, pass: String): String{

        return networkLogin.login(user,pass)
    }
}