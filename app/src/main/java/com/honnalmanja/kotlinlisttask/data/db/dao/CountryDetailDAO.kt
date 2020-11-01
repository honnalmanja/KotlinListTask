package com.honnalmanja.kotlinlisttask.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.honnalmanja.kotlinlisttask.data.model.entity.CountryDetail

@Dao
interface CountryDetailDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllCountryDetails(countryDetailList: List<CountryDetail>)

    @Query("SELECT * FROM country_detail")
    suspend fun getCountryDetails()

    @Query("DELETE FROM country_detail")
    suspend fun deleteCountryDetails()

   /* @Query("SELECT * FROM country_detail WHERE customer_id = :countryID")
    suspend fun getCountryDetails(countryID: Int)

    @Query("DELETE FROM country_detail WHERE customer_id = :countryID")
    suspend fun deleteCountryDetails(countryID: Int)*/
}