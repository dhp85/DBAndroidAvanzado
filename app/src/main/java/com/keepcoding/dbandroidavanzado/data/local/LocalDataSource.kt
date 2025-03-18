package com.keepcoding.dbandroidavanzado.data.local

import android.content.Context
import androidx.room.Room
import com.keepcoding.dbandroidavanzado.domain.entities.HeroModelDto
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val dao: HeroDao
) {

    fun init(context: Context){

    }

    suspend fun getAllHeros(): List<HeroModelLocal> {
        return dao.getAllHeros()
    }

    suspend fun insertAll(heros: List<HeroModelLocal>) {
        dao.insertAll(heros)

    }

}