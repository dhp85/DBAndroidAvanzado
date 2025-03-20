package com.keepcoding.dbandroidavanzado.data.Network.base

import com.keepcoding.dbandroidavanzado.auth.CredentialsProvider
import com.keepcoding.dbandroidavanzado.data.Network.networkapi.LoginApi
import javax.inject.Inject

class NetworkLogin @Inject constructor(
    private val api: LoginApi,
    private val credentialsProvider: CredentialsProvider
){

    suspend fun login(user: String, pass: String): Result<String> = runCatching {
        credentialsProvider.setCredentials(user,pass)
        api.login()
    }
}