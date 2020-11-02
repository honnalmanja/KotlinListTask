package com.honnalmanja.kotlinlisttask.di

import com.honnalmanja.kotlinlisttask.data.repository.CountryRepositoryImpl
import com.honnalmanja.kotlinlisttask.data.repository.source.CountryCacheDataSource
import com.honnalmanja.kotlinlisttask.data.repository.source.CountryLocalDataSource
import com.honnalmanja.kotlinlisttask.data.repository.source.CountryRemoteDataSource
import com.honnalmanja.kotlinlisttask.domain.CountryRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideCountryRepository(
            countryCacheDataSource: CountryCacheDataSource,
            countryLocalDataSource: CountryLocalDataSource,
            countryRemoteDataSource: CountryRemoteDataSource
    ): CountryRepository {
        return CountryRepositoryImpl(
                countryCacheDataSource, countryLocalDataSource,countryRemoteDataSource
        )
    }

}