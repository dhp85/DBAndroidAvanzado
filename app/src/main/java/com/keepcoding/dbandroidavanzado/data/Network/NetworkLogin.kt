package com.keepcoding.dbandroidavanzado.data.Network

import com.keepcoding.dbandroidavanzado.data.Network.model.CredentialsProvider
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