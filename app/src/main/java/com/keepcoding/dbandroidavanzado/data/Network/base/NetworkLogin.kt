package com.keepcoding.dbandroidavanzado.data.Network.base

import com.keepcoding.dbandroidavanzado.auth.CredentialsProvider
import com.keepcoding.dbandroidavanzado.data.Network.networkapi.LoginApi
import javax.inject.Inject

class NetworkLogin @Inject constructor(
    private val api: dagger.Lazy <LoginApi>,
    private val credentialsProvider: CredentialsProvider
){

    suspend fun login(user: String, pass: String): String {
        credentialsProvider.setCredentials(user,pass)
        return api.get().login()

    }
}