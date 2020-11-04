package com.honnalmanja.kotlinlisttask.data.repository.impl

import com.honnalmanja.kotlinlisttask.data.db.dao.CountryDAO
import com.honnalmanja.kotlinlisttask.data.db.dao.CountryDetailDAO
import com.honnalmanja.kotlinlisttask.data.model.entity.Country
import com.honnalmanja.kotlinlisttask.data.model.entity.CountryDetail
import com.honnalmanja.kotlinlisttask.data.model.remote.About
import com.honnalmanja.kotlinlisttask.data.model.remote.Canada
import com.honnalmanja.kotlinlisttask.data.repository.source.CountryLocalDataSource
import com.honnalmanja.kotlinlisttask.utils.LogUtils

class CountryLocalDataSourceImpl(
        private val countryDAO: CountryDAO,
        private val countryDetailDAO: CountryDetailDAO
): CountryLocalDataSource {
    override suspend fun saveACountry(canada: Canada?) {
        val country = Country(canada?.title ?: "")
        countryDAO.saveCountry(country)
        if(country.title.isNotEmpty() and canada?.abouts!!.isNotEmpty()){
            LogUtils.logD("CountryLocalDataSourceImpl", "ABout Size: ${canada.abouts.size}")
            saveCountryDetails(canada.abouts, country.countryID)
        }
    }

    private suspend fun saveCountryDetails(aboutList: List<About>,  countryID: Int) {
        val countryDetailList = ArrayList<CountryDetail>()
        for (about in aboutList){
            if(about.title != null){
                countryDetailList.add(CountryDetail(
                        about.description,
                        about.imageHref,
                        about.title,
                        countryID
                ))
            }
        }
        countryDetailDAO.saveAllCountryDetails(countryDetailList)
    }

    override suspend fun getACountry(): Country {
        return countryDAO.getACountry()
    }

    override suspend fun getCountryDetails(): List<CountryDetail> {
        return countryDetailDAO.getCountryDetails()
    }

    override suspend fun clearAllCountry() {
        countryDAO.deleteCountry()
    }

    override suspend fun clearAllCountryDetails() {
        countryDetailDAO.deleteCountryDetails()
    }
}