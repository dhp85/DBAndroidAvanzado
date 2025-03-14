package com.keepcoding.dbandroidavanzado.di

import android.content.Context
import androidx.room.Room
import com.keepcoding.dbandroidavanzado.data.local.HeroDao
import com.keepcoding.dbandroidavanzado.data.local.HeroDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    fun provideSuperDatabase(@ApplicationContext context: Context): HeroDataBase {
        return Room.databaseBuilder(
            context,
            HeroDataBase::class.java, "database-name"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideSuperDao(db: HeroDataBase): HeroDao {
        return db.heroDao()
    }
}