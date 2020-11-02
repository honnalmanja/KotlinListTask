package com.honnalmanja.kotlinlisttask.di

import com.honnalmanja.kotlinlisttask.data.api.TaskServiceAPI
import com.honnalmanja.kotlinlisttask.data.repository.impl.CountryRemoteDataSourceImpl
import com.honnalmanja.kotlinlisttask.data.repository.source.CountryRemoteDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RemoteDataModule {

    @Singleton
    @Provides
    fun provideCountryRemoteDataSource(taskServiceAPI: TaskServiceAPI):
            CountryRemoteDataSource {
        return CountryRemoteDataSourceImpl(taskServiceAPI)
    }

}