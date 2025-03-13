package com.keepcoding.dbandroidavanzado.data.local

import android.content.Context
import androidx.room.Room
import com.keepcoding.dbandroidavanzado.entities.HeroModelDto

class LocalDataSource {

    private lateinit var db: HeroDataBase

    fun init(context: Context){
        db = Room.databaseBuilder(
            context,
            HeroDataBase::class.java, "database-name"

        ).build()
    }

    suspend fun getAllHeros(): List<HeroModelLocal> {
        return db.heroDao().getAllHeros()
    }

    suspend fun insertAll(heros: List<HeroModelLocal>) {
        db.heroDao().insertAll(heros)

    }

}