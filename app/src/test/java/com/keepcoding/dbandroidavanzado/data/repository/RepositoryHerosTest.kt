package com.keepcoding.dbandroidavanzado.data.repository

import com.google.common.truth.Truth
import com.keepcoding.dbandroidavanzado.data.Network.base.NetworkHeros
import com.keepcoding.dbandroidavanzado.data.local.LocalDataSource
import com.keepcoding.dbandroidavanzado.data.repository.RepositoryHeros
import com.keepcoding.dbandroidavanzado.data.generators.getHerosLocal
import com.keepcoding.dbandroidavanzado.data.generators.getHerosMock
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class RepositoryHerosTest {

    //SUT

    private lateinit var repository: RepositoryHeros

    //Dependencias
    private lateinit var localdata: LocalDataSource
    private lateinit var network: NetworkHeros

    @Before
    fun setUp() {
        localdata = mockk()
        network = mockk()
        repository = RepositoryHeros(network, localdata)
    }

    @Test
    fun `Given filled local database When getHeros Then has success results`() = runBlocking {


        coEvery { localdata.getAllHeros() } returns getHerosLocal()

        val result = repository.getHeros()

        Assert.assertTrue(result.isNotEmpty())
        Truth.assertThat(result).containsExactlyElementsIn(getHerosMock())
        Assert.assertTrue(result.size == 10)
        Assert.assertTrue(result.first().name == "name 0")
        Assert.assertTrue(result.last().name == "name 9")

    }

    @Test
    fun `Given empty local database When getHeros Then makes remote call and fills local database`() =
        runBlocking {

            coEvery { localdata.getAllHeros() } returns emptyList()

            val remoteHeros = getHerosMock()
            coEvery { network.getHeros() } returns Result.success(remoteHeros)

            coEvery { localdata.insertAll(any()) } just runs

            val result = repository.getHeros()

            // Verifications
            coVerify { network.getHeros() }
            coVerify { localdata.insertAll(remoteHeros.map { it.toHeroModelLocal() }) }

            // Assertions
            Assert.assertTrue(result.isNotEmpty())
            Truth.assertThat(result).containsExactlyElementsIn(remoteHeros)
            Assert.assertTrue(result.size == remoteHeros.size)
        }
}