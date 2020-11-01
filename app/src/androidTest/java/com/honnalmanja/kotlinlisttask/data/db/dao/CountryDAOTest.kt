package com.honnalmanja.kotlinlisttask.data.db.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.honnalmanja.kotlinlisttask.data.db.CountryDB
import com.honnalmanja.kotlinlisttask.data.model.entity.Country
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CountryDAOTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var dao: CountryDAO
    private lateinit var database: CountryDB

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            CountryDB::class.java
        ).build()
        dao = database.countryDAO()
    }

    @After
    fun tearDown() {
        database.close()
    }

    /**
     *  Test function to check insert and read Query is working
     */
    @Test
    fun saveCountryTest() = runBlocking{
        //add country
        val country = Country("Canada")
        dao.saveCountry(country)

        // get a country
        val aCountry: Country = dao.getACountry()

        // check for ID and title if same test passes
        Truth.assertThat(aCountry.countryID).isEqualTo(country.countryID)
        Truth.assertThat(aCountry.title).isEqualTo(country.title)
    }



    /**
     * Test function to check delete Query is working
     */
    @Test
    fun deleteCountryTest() = runBlocking{

        //add country
        val country = Country("Canada")
        dao.saveCountry(country)

        // delete country
        dao.deleteCountry()

        // get all country
        val allCountry = dao.getAllCountry()

        // check for empty passes if empty
        Truth.assertThat(allCountry).isEmpty()
    }
}