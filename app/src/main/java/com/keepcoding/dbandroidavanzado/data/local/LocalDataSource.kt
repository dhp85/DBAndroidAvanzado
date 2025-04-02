package com.keepcoding.dbandroidavanzado.data.local


import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val dao: HeroDao
) {


    suspend fun getAllHeros(): List<HeroModelLocal> {
        return dao.getAllHeros()
    }

    suspend fun insertAll(heros: List<HeroModelLocal>) {
        dao.insertAll(heros)

    }

    suspend fun updateFavorite(id: String, isFavorite: Boolean) {
        dao.updateFavorite(id, isFavorite)
    }

    suspend fun getFavoriteStatus(id: String): Boolean {
        return dao.getFavoriteStatus(id)

    }

}