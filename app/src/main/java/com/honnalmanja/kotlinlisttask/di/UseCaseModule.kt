package com.honnalmanja.kotlinlisttask.di

import com.honnalmanja.kotlinlisttask.domain.CountryRepository
import com.honnalmanja.kotlinlisttask.domain.CountryUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {

    @Singleton
    @Provides
    fun provideCountryUseCase(countryRepository: CountryRepository): CountryUseCase {
        return CountryUseCase(countryRepository)
    }
}