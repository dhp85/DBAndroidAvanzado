package com.keepcoding.dbandroidavanzado.mockwebserver


import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class LoginDispacher: Dispatcher() {

    val token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.ey"

    var forceError = false

    override fun dispatch(request: RecordedRequest): MockResponse {

        return if (forceError) {
            MockResponse().setResponseCode(404)
        }else {
            when (request.path) {
                "/api/auth/login" -> MockResponse().setResponseCode(200).setBody(token)
                else -> MockResponse().setResponseCode(404)
            }
        }
    }
}