package com.keepcoding.dbandroidavanzado.data.remote

import com.keepcoding.dbandroidavanzado.data.Network.base.NetworkHeros
import com.keepcoding.dbandroidavanzado.data.Network.networkapi.HerosApi
import com.keepcoding.dbandroidavanzado.mockwebserver.HerosDispacher
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MockWebserverNetworkHeros {

    // Dependencias

    private lateinit var api: HerosApi
    private lateinit var mockWebServer: MockWebServer
    private lateinit var dispacher: HerosDispacher

    // SUT
    private lateinit var network: NetworkHeros

    @Before
    fun setUp(){
        dispacher = HerosDispacher()

        mockWebServer = MockWebServer()
        mockWebServer.dispatcher = dispacher
        mockWebServer.start()

        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

        val httpClient = OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY

        }).build()

        api = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .client(httpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(HerosApi::class.java)

        network = NetworkHeros(api)
    }

    @Test
    fun`test return all heros in call Api`() = runBlocking {

        dispacher.forceError = false


        val heros = network.getHeros().getOrThrow()

        assertTrue(heros.isNotEmpty())
        assertTrue(heros.size == 15)
        assertTrue(heros.first().name == "Androide 17")
        assertTrue(heros.last().name == "Vegeta")

    }

    @Test(expected = Exception::class)
    fun `Test return error in call Api`() = runBlocking {
        // Configurar el dispacher para que devuelva un error
        dispacher.forceError = true

        // Llamar a la función que queremos probar
        val heros = network.getHeros().getOrThrow()

        //Assertar el resultado

        assertTrue(heros.isNotEmpty())
        assertTrue(heros.size == 15)

    }

    @After
    fun tearDown(){
        mockWebServer.shutdown()
    }
}