package com.honnalmanja.kotlinlisttask.data.repository


import com.honnalmanja.kotlinlisttask.data.model.remote.About
import com.honnalmanja.kotlinlisttask.data.model.remote.Canada

interface CountryLocalDataSource {

    suspend fun saveACountry(canada: Canada)

    suspend fun saveCountryDetails(aboutList: List<About>)

    suspend fun clearAllCountry()

    suspend fun clearAllCountryDetails()

}