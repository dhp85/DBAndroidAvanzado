package com.keepcoding.dbandroidavanzado.data.repository

import android.content.Context
import android.util.Log
import com.keepcoding.dbandroidavanzado.data.Network.NetworkDetail
import com.keepcoding.dbandroidavanzado.data.Network.NetworkHeros
import com.keepcoding.dbandroidavanzado.data.local.LocalDataSource
import com.keepcoding.dbandroidavanzado.domain.entities.HeroModelDto
import javax.inject.Inject

class RepositoryDetail@Inject constructor(
    private val networkDetail: NetworkDetail) {

    suspend fun getHero(name: String): List<HeroModelDto> {

            val remoteHero = networkDetail.getHero(name)
            Log.d("RepositoryHeros", "remoteHeros: $remoteHero")
            return remoteHero
    }
}