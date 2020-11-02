package com.honnalmanja.kotlinlisttask.data.repository.source

import com.honnalmanja.kotlinlisttask.data.model.remote.Canada

interface CountryCacheDataSource {

    suspend fun getCountryWithDetails(): Canada?

    suspend fun saveCountryWithDetails(canada: Canada?)

}