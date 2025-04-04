package com.keepcoding.dbandroidavanzado.auth

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CredentialsProvider @Inject constructor() {

    var username: String = ""
    var password: String = ""
    private var token: String = ""

    fun setCredentials(username: String, password: String) {
        this.username = username
        this.password = password
    }

    fun setToken(token: String) {
        this.token = token
    }

    fun getToken(): String {
        return token

    }

}