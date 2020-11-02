package com.honnalmanja.kotlinlisttask.data.repository

import com.honnalmanja.kotlinlisttask.data.model.entity.Country
import com.honnalmanja.kotlinlisttask.data.model.remote.About
import com.honnalmanja.kotlinlisttask.data.model.remote.Canada
import com.honnalmanja.kotlinlisttask.data.repository.source.CountryCacheDataSource
import com.honnalmanja.kotlinlisttask.data.repository.source.CountryLocalDataSource
import com.honnalmanja.kotlinlisttask.data.repository.source.CountryRemoteDataSource
import com.honnalmanja.kotlinlisttask.domain.CountryRepository
import com.honnalmanja.kotlinlisttask.utils.LogUtils

class CountryRepositoryImpl(
        private val countryCacheDataSource: CountryCacheDataSource,
        private val countryLocalDataSource: CountryLocalDataSource,
        private val countryRemoteDataSource: CountryRemoteDataSource
) : CountryRepository {

    private val _TAG = "CountryRepositoryImpl"

    /**
     * Function to call to get Country detail list when activity is open
     * @return canada: Updated country with detail List
     */
    override suspend fun getCountryWithDetails(): Canada? {
        return getCountryDetailsFromCache()
    }

    /**
     * Function to call when u want to update the Country detail list
     * @return canada: Updated country with detail List
     */
    override suspend fun updateCountryWithDetails(): Canada? {
        val newCanada = getCountryDetailsFromAPI()
        countryCacheDataSource.saveCountryWithDetails(newCanada)
        saveCountryToLocalDB(newCanada)
        return newCanada
    }

    private suspend fun getCountryDetailsFromAPI(): Canada? {
        val response = countryRemoteDataSource.getAllCountryDetail()
        return try {
            response.body()
        } catch (e: Exception){
            LogUtils.logE(_TAG, "${e.message}")
            return null
        }
    }

    private suspend fun getCountryDetailsFromDB() : Canada? {
        val country: Country = countryLocalDataSource.getACountry()
        val countryDetails = countryLocalDataSource.getCountryDetails()
        val aboutList = ArrayList<About>()
        countryDetails.forEach {
            aboutList.add(About(
                    it.description, it.imageHref, it.title
            ))
        }

        // if data not present in LocalDB get from API
        if(country.title.isEmpty() or aboutList.isEmpty()){
            val canada = getCountryDetailsFromAPI()
            return saveCountryToLocalDB(canada)
        }
        return Canada(aboutList, country.title)
    }

    private suspend fun saveCountryToLocalDB(newCanada: Canada?) : Canada ?{
        return if(newCanada == null){
            null
        } else {
            countryLocalDataSource.clearAllCountry()
            countryLocalDataSource.clearAllCountryDetails()
            countryLocalDataSource.saveACountry(newCanada)
            newCanada
        }
    }

    private suspend fun getCountryDetailsFromCache() : Canada? {

        var canada: Canada? = null
        try {
            canada = countryCacheDataSource.getCountryWithDetails()

            // If data is not present in cache get from Local database
            if(canada == null) {
                canada = getCountryDetailsFromDB()
                countryCacheDataSource.saveCountryWithDetails(canada)
            }
        } catch (e: Exception){
            LogUtils.logE(_TAG, "${e.message}")
        }

        return canada
    }

}