package com.honnalmanja.kotlinlisttask.di

import com.honnalmanja.kotlinlisttask.data.repository.impl.CountryCacheDataSourceImpl
import com.honnalmanja.kotlinlisttask.data.repository.source.CountryCacheDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheDataModule {

    @Singleton
    @Provides
    fun provideCountryCacheDataSource(): CountryCacheDataSource{
        return CountryCacheDataSourceImpl()
    }

}