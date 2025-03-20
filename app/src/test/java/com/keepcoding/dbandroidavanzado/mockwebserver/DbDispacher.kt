package com.keepcoding.dbandroidavanzado.mockwebserver

import com.keepcoding.dbandroidavanzado.utils.parseJson
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest


class DbDispacher: Dispatcher() {
    override fun dispatch(request: RecordedRequest): MockResponse {

       return when (request.path) {
           "/api/heros/all" -> MockResponse().setResponseCode(200).setBody(parseJson("json/heros.json"))
           else -> MockResponse().setResponseCode(404)
       }
    }
}