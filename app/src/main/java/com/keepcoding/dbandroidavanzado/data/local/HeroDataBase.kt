package com.keepcoding.dbandroidavanzado.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities =[HeroModelLocal::class], version = 1)
abstract class HeroDataBase : RoomDatabase() {
    abstract fun heroDao(): HeroDao

}