package com.honnalmanja.kotlinlisttask.data.repository.source


import com.honnalmanja.kotlinlisttask.data.model.entity.Country
import com.honnalmanja.kotlinlisttask.data.model.entity.CountryDetail
import com.honnalmanja.kotlinlisttask.data.model.remote.About
import com.honnalmanja.kotlinlisttask.data.model.remote.Canada

interface CountryLocalDataSource {

    suspend fun saveACountry(canada: Canada?)

    suspend fun getACountry(): Country

    suspend fun getCountryDetails(): List<CountryDetail>

    suspend fun clearAllCountry()

    suspend fun clearAllCountryDetails()

}