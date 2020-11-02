package com.honnalmanja.kotlinlisttask.data.repository.impl

import com.honnalmanja.kotlinlisttask.data.model.remote.Canada
import com.honnalmanja.kotlinlisttask.data.repository.source.CountryCacheDataSource

class CountryCacheDataSourceImpl : CountryCacheDataSource {

    private var canada: Canada? = null

    override suspend fun getCountryWithDetails(): Canada? {
        return canada
    }

    override suspend fun saveCountryWithDetails(canada: Canada?) {
        this.canada = canada
    }
}