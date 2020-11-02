package com.honnalmanja.kotlinlisttask.data.repository.source

import com.honnalmanja.kotlinlisttask.data.model.entity.Country
import com.honnalmanja.kotlinlisttask.data.model.remote.Canada
import retrofit2.Response

interface CountryRemoteDataSource {

    suspend fun getAllCountryDetail() : Response<Canada>

}