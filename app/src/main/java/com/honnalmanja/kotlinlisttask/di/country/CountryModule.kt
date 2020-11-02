package com.honnalmanja.kotlinlisttask.di.country

import com.honnalmanja.kotlinlisttask.domain.CountryUseCase
import com.honnalmanja.kotlinlisttask.presentation.CountryViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class CountryModule {

    @CountryScope
    @Provides
    fun provideCountryViewModelFactory(
            countryUseCase: CountryUseCase
    ): CountryViewModelFactory {
        return CountryViewModelFactory(countryUseCase)
    }
}