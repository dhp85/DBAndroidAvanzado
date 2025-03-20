package com.keepcoding.dbandroidavanzado.data.repository

import android.util.Log
import com.keepcoding.dbandroidavanzado.data.Network.base.NetworkDetail
import com.keepcoding.dbandroidavanzado.domain.entities.HeroModelDto
import javax.inject.Inject

class RepositoryDetail@Inject constructor(
    private val networkDetail: NetworkDetail
) {

    suspend fun getHero(name: String): Result<List<HeroModelDto>> = runCatching {

        networkDetail.getHero(name)
            .getOrElse { throwable ->
                Result.failure<List<HeroModelDto>>(throwable)
                Log.e(
                    "RepositoryDetail", "Error getting hero in detail from network",
                    throwable)
                throw throwable
            }

    }
}