package com.franalarza.tryavanzado.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.franalarza.tryavanzado.data.local.models.HeroLocal

@Dao
interface HeroDAO {
    @Query("SELECT * FROM heroes")
    fun getAllHeroes(): List<HeroLocal>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(heroes: List<HeroLocal>)
}