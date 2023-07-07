package com.alexjlockwood.twentyfortyeight.di

import android.content.Context
import com.alexjlockwood.twentyfortyeight.repository.GameRepository
import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.Singleton

object MainApplicationModule {

    @Singleton
    @Provides
    fun provideGameRepository(application: Context): GameRepository {
        return GameRepository(application)
    }
}
