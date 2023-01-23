package com.franalarza.tryavanzado.di

import android.content.Context
import androidx.room.Room
import com.franalarza.tryavanzado.data.local.HeroDAO
import com.franalarza.tryavanzado.data.local.HeroDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    fun providesDataBase(@ApplicationContext context: Context): HeroDataBase {
        return Room.databaseBuilder(
            context,
            HeroDataBase::class.java, "HeroDataBase"
        ).build()
    }

    @Provides
    fun provideDAO(dataBase: HeroDataBase): HeroDAO {
        return dataBase.getHeroDAO()
    }
}