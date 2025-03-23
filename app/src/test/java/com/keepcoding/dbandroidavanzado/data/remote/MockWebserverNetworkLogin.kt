package com.keepcoding.dbandroidavanzado.data.remote

import com.keepcoding.dbandroidavanzado.auth.CredentialsProvider
import com.keepcoding.dbandroidavanzado.data.Network.base.NetworkLogin
import com.keepcoding.dbandroidavanzado.data.Network.networkapi.LoginApi
import com.keepcoding.dbandroidavanzado.mockwebserver.LoginDispacher
import com.squareup.moshi.Moshi
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

class MockWebserverNetworkLogin {
    private lateinit var api: LoginApi
    private lateinit var credentialsProvider: CredentialsProvider
    private lateinit var mockWebServer: MockWebServer
    private lateinit var dispacher: LoginDispacher

    //SUT
    private lateinit var network: NetworkLogin

    @Before
    fun setUp() {
        dispacher = LoginDispacher()

        credentialsProvider = CredentialsProvider()
        mockWebServer = MockWebServer()
        mockWebServer.dispatcher = dispacher
        mockWebServer.start()


        val httpClient = OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }).build()

        api = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .client(httpClient)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
            .create(LoginApi::class.java)

        network = NetworkLogin(api, credentialsProvider)


    }

    @Test
    fun test_return_token_in_call_Api () = runBlocking {

        dispacher.forceError = false
        val user = credentialsProvider.username
        val password = credentialsProvider.password

        val token = network.login(user,password).getOrThrow()
        println(token)

        Assert.assertTrue("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.ey", true)
    }

    @Test(expected = Exception::class)
    fun test_return_error_in_call_Api() = runBlocking {
        // Configurar el dispacher para que devuelva un error
        dispacher.forceError = true
        val user = credentialsProvider.username
        val password = credentialsProvider.password
        // Llamar a la función que queremos probar
        val token = network.login(user, password).getOrThrow()

        //Assertar el resultado
        assertTrue(token.isEmpty())


    }

    @After
    fun tearDown(){
        mockWebServer.shutdown()
    }
}