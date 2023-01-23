package com.franalarza.tryavanzado.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.franalarza.tryavanzado.data.local.models.HeroLocal


@Database(entities = [HeroLocal::class], version = 1, exportSchema = false)
abstract class HeroDataBase : RoomDatabase() {
    abstract fun getHeroDAO(): HeroDAO
}
