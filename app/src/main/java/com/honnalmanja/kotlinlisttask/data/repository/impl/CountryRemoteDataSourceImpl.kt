package com.honnalmanja.kotlinlisttask.data.repository.impl

import com.honnalmanja.kotlinlisttask.data.api.TaskServiceAPI
import com.honnalmanja.kotlinlisttask.data.model.remote.Canada
import com.honnalmanja.kotlinlisttask.data.repository.source.CountryRemoteDataSource
import retrofit2.Response

class CountryRemoteDataSourceImpl(
        private val taskServiceAPI: TaskServiceAPI
): CountryRemoteDataSource {
    override suspend fun getAllCountryDetail(): Response<Canada> = taskServiceAPI.getAPIData()
}