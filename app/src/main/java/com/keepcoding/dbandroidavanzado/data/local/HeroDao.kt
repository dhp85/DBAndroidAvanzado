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

    @Query("SELECT favorite FROM heros WHERE id = :heroId")
    suspend fun getFavoriteStatus(heroId: String): Boolean

    @Query("UPDATE heros SET favorite = :isFavorite WHERE id = :heroId")
    suspend fun updateFavorite(heroId: String, isFavorite: Boolean)


}