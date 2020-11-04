package com.honnalmanja.kotlinlisttask.di.core

import com.honnalmanja.kotlinlisttask.di.country.CountrySubComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, RetrofitModule::class, RoomDBModule::class,
    CacheDataModule::class, RemoteDataModule::class, LocalDataModule::class,
    RepositoryModule::class, UseCaseModule::class
])
interface AppComponent {

    fun countrySubComponent(): CountrySubComponent.Factory
}