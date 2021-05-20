package com.ahmedtikiwa.felinelove.di

import com.ahmedtikiwa.felinelove.repository.CatApiRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun providesCatApiRepository() : CatApiRepository {
        return CatApiRepository()
    }
}