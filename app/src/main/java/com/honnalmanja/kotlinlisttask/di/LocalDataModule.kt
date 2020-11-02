package com.honnalmanja.kotlinlisttask.di

import com.honnalmanja.kotlinlisttask.data.db.dao.CountryDAO
import com.honnalmanja.kotlinlisttask.data.db.dao.CountryDetailDAO
import com.honnalmanja.kotlinlisttask.data.repository.impl.CountryLocalDataSourceImpl
import com.honnalmanja.kotlinlisttask.data.repository.source.CountryLocalDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalDataModule {

    @Singleton
    @Provides
    fun provideCountryLocalDataSource(countryDAO: CountryDAO, countryDetailDAO: CountryDetailDAO)
            : CountryLocalDataSource {
        return CountryLocalDataSourceImpl(countryDAO, countryDetailDAO)
    }

}