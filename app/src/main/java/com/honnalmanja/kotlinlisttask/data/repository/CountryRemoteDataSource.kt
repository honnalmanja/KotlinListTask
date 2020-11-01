package com.honnalmanja.kotlinlisttask.data.repository

import com.honnalmanja.kotlinlisttask.data.model.entity.Country
import retrofit2.Response

interface CountryRemoteDataSource {

    suspend fun getAllCountryDetail() : Response<Country>

}