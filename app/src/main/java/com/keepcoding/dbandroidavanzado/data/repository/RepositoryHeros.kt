package com.keepcoding.dbandroidavanzado.data.repository

import android.content.Context
import android.util.Log
import com.keepcoding.dbandroidavanzado.data.Network.base.NetworkHeros
import com.keepcoding.dbandroidavanzado.data.local.LocalDataSource
import com.keepcoding.dbandroidavanzado.domain.entities.HeroModelDto
import javax.inject.Inject

class RepositoryHeros @Inject constructor(
    private val networkHeros: NetworkHeros,
    private val localDataHeros: LocalDataSource) {


    fun init(context: Context) {
        localDataHeros.init(context)
    }
    suspend fun getHeros(): List<HeroModelDto> {
        val localHeros = localDataHeros.getAllHeros()

      return if (localHeros.isEmpty()) {
            networkHeros.getHeros().onSuccess { remoteHeros ->
                localDataHeros.insertAll(remoteHeros.map { it.toHeroModelLocal() })
            }.getOrElse { throwable ->
                Log.e("RepositoryHeros", "Error getting heros from network", throwable)
                emptyList()
            }
        } else {
            localHeros.map { it.toHeroModelDto() }
        }
    }
}