package com.honnalmanja.kotlinlisttask.di

import dagger.Component

@Component(modules = [AppModule::class, RetrofitModule::class, RoomDBModule::class,
    CacheDataModule::class, RemoteDataModule::class, LocalDataModule::class,
    RepositoryModule::class, UseCaseModule::class
])
interface AppComponent {
}