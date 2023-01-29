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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHero(hero: HeroLocal)

    @Query("SELECT * FROM heroes WHERE id LIKE :id")
    fun getHeroFromLocal(id: String): HeroLocal

    @Query("UPDATE heroes SET favorite = NOT favorite WHERE id=:id")
    fun toggleFavoriteInLocal(id: String)
}