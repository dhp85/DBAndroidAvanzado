package com.keepcoding.dbandroidavanzado.data.repository

import com.keepcoding.dbandroidavanzado.data.Network.base.NetworkLogin
import io.mockk.clearMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class RepositoryLoginTest {

    private lateinit var repository: RepositoryLogin

    //dependencias
    private lateinit var network: NetworkLogin

    @Before

    fun setup() {
        network = mockk()
        repository = RepositoryLogin(network)

    }

    @Test
    fun test_funtion_login_Then_has_success_results() = runBlocking{

        val user = "user"
        val pass = "pass"
        val token = "token"

        coEvery { network.login(user, pass) } returns Result.success(token)

        val result = repository.login(user, pass)

        Assert.assertTrue(result.isSuccess)
        Assert.assertTrue(result.getOrNull() == token)

    }

    @Test
    fun test_funtion_login_Then_has_exception_results() = runBlocking{

        val user = "user"
        val pass = "pass"
        val exception = Exception("Error")

        coEvery { network.login(user, pass) } returns Result.failure(exception)

        val result = repository.login(user, pass)

        Assert.assertTrue(result.isFailure)
    }

    @After
    fun tearDown() {
        clearMocks(network) // Borra las simulaciones de MockK
    }
}