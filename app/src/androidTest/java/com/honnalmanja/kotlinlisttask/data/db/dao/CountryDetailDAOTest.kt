package com.honnalmanja.kotlinlisttask.data.db.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.honnalmanja.kotlinlisttask.data.db.CountryDB
import com.honnalmanja.kotlinlisttask.data.model.entity.Country
import com.honnalmanja.kotlinlisttask.data.model.entity.CountryDetail
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CountryDetailDAOTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var countryDetailDAO: CountryDetailDAO
    private lateinit var countryDAO: CountryDAO
    private lateinit var database: CountryDB

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(), CountryDB::class.java
        ).build()
        countryDetailDAO = database.countryDetailDAO()
        countryDAO = database.countryDAO()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun saveCountryDetailTest() = runBlocking {

        // add a country
        val country = Country("Germany")
        countryDAO.saveCountry(country)

        // get country ID
        val countryID = country.countryID

        // make list of country details
        val countryDetailList = listOf(
            CountryDetail("Description 1", "imgRef 1", "Place 1", countryID),
            CountryDetail("Description 2", "imgRef 2", "Place 2", countryID)
        )

        // save country detail list
        countryDetailDAO.saveAllCountryDetails(countryDetailList)

        // get all country details
        val allCountryDetailList = countryDetailDAO.getCountryDetails()

        // check for saved and got back size to check all saved
        Truth.assertThat(allCountryDetailList.size).isEqualTo(countryDetailList.size)
    }

    @Test
    fun savingAndGettingSameCountryDetails() = runBlocking {

        // add a country
        val country = Country("Germany")
        countryDAO.saveCountry(country)

        // get country ID
        val countryID = country.countryID

        // create list of country details
        val countryDetailList = listOf(
            CountryDetail("Description 1", "imgRef 1", "Place 1", countryID),
            CountryDetail("Description 2", "imgRef 2", "Place 2", countryID)
        )

        // save all country details
        countryDetailDAO.saveAllCountryDetails(countryDetailList)

        // get saved country details
        val allCountryDetailList = countryDetailDAO.getCountryDetails()

        // check weather each description is same
        for ((i, countryDetail) in allCountryDetailList.withIndex()){
            Truth.assertThat(countryDetail.description).isEqualTo(countryDetailList[i].description)
        }

    }

    @Test
    fun deleteAllCountryDetail() = runBlocking{

        // add country
        val country = Country("Germany")
        countryDAO.saveCountry(country)
        val countryID = country.countryID

        // add country details
        val countryDetailList = listOf(
            CountryDetail("Description 1", "imgRef 1", "Place 1", countryID),
            CountryDetail("Description 2", "imgRef 2", "Place 2", countryID)
        )
        countryDetailDAO.saveAllCountryDetails(countryDetailList)

        // delete all countries
        countryDetailDAO.deleteCountryDetails()

        // get all countries
        val allCountryDetailList = countryDetailDAO.getCountryDetails()

        // check for empty list to pass test
        Truth.assertThat(allCountryDetailList).isEmpty()
    }
}