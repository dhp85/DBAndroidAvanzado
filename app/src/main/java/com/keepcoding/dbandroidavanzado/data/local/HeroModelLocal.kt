package com.keepcoding.dbandroidavanzado.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.keepcoding.dbandroidavanzado.entities.HeroModelDto


@Entity(tableName = "heros")
data class HeroModelLocal(
    @PrimaryKey @ColumnInfo(name = "id")val id: String,
    @ColumnInfo(name = "name")val name: String,
    @ColumnInfo(name = "photo")val photo: String,
    @ColumnInfo(name = "description")val description: String,
    @ColumnInfo(name = "favorite")val favorite: Boolean

){
    fun toHeroModel(): HeroModelDto {
        return HeroModelDto(
            id = id,
            name = name,
            photo = photo,
            description = description,
            favorite = favorite
        )
    }
}
