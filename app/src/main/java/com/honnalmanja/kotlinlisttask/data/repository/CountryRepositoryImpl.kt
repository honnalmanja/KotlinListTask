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
        var newCanada = getCountryDetailsFromAPI()
        saveCountryToLocalDB(newCanada)
        newCanada = getCountryDetailsFromDB()
        countryCacheDataSource.clearAll()
        countryCacheDataSource.saveCountryWithDetails(newCanada)
        return newCanada
    }

    private suspend fun getCountryDetailsFromAPI(): Canada? {
        LogUtils.logD(_TAG, "getCountryDetailsFromAPI")
        val response = countryRemoteDataSource.getAllCountryDetail()
        return try {
            response.body()
        } catch (e: Exception){
            LogUtils.logE(_TAG, "${e.message}")
            return null
        }
    }

    private suspend fun getCountryDetailsFromDB() : Canada? {
        val country: Country? = countryLocalDataSource.getACountry()

        return if(country == null){
            LogUtils.logD(_TAG, "getCountryDetailsFromDB inside if")
            val canada = getCountryDetailsFromAPI()
            saveCountryToLocalDB(canada)
        } else {
            LogUtils.logD(_TAG, "getCountryDetailsFromDB inside else")
            val countryDetails = countryLocalDataSource.getCountryDetails()
            val aboutList = ArrayList<About>()
            countryDetails.forEach {
                if(!it.title.isNullOrEmpty()){
                    aboutList.add(About(
                            it.description, it.imageHref, it.title
                    ))
                }
            }
            Canada(aboutList, country.title)
        }

    }

    private suspend fun saveCountryToLocalDB(newCanada: Canada?) : Canada ?{
        return if(newCanada == null){
            null
        } else {
            LogUtils.logD(_TAG, "Size: ${newCanada.abouts.size}")
            countryLocalDataSource.clearAllCountry()
            countryLocalDataSource.clearAllCountryDetails()
            countryLocalDataSource.saveACountry(newCanada)
            getCountryDetailsFromDB()
        }
    }

    private suspend fun getCountryDetailsFromCache() : Canada? {

        return try {
            var canada = countryCacheDataSource.getCountryWithDetails()
            LogUtils.logD(_TAG, "indide try")
            // If data is not present in cache get from Local database
            if(canada != null) {
                canada
            } else {
                canada = getCountryDetailsFromDB()
                countryCacheDataSource.clearAll()
                countryCacheDataSource.saveCountryWithDetails(canada)
                canada
            }
        } catch (e: Exception){
            LogUtils.logE(_TAG, "getCountryDetailsFromCache ${e.printStackTrace()}")
            null
        }
    }

}