package com.keepcoding.dbandroidavanzado.data.repository

import android.content.Context
import android.util.Log
import com.keepcoding.dbandroidavanzado.data.Network.NetworkHeros
import com.keepcoding.dbandroidavanzado.data.local.LocalDataSource
import com.keepcoding.dbandroidavanzado.entities.HeroModelDto

class RepositoryHeros {

    private val networkHeros = NetworkHeros()

    private val localDataHeros: LocalDataSource = LocalDataSource()

    fun init(context: Context) {
        localDataHeros.init(context)
    }

    suspend fun getHeros(): List<HeroModelDto> {
        val localHeros = localDataHeros.getAllHeros()

        if (localHeros.isEmpty()) {
            val remoteHeros = networkHeros.getHeros()

            Log.d("RepositoryHeros", "remoteHeros: $remoteHeros")
            localDataHeros.insertAll(remoteHeros.map { it.toHeroModelLocal()})
            return remoteHeros
        }
        return localHeros.map { it.toHeroModel() }
    }




}