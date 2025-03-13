package com.keepcoding.dbandroidavanzado.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface HeroDao {

    @Query("SELECT * FROM heros")
    suspend fun getAllHeros(): List<HeroModelLocal>

    @Insert
    suspend fun insertAll(heros: List<HeroModelLocal>)

}