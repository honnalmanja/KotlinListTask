package com.honnalmanja.kotlinlisttask.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.honnalmanja.kotlinlisttask.data.model.entity.Country

@Dao
interface CountryDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCountry(country: Country)

    @Query("SELECT * FROM country")
    suspend fun getCountry() : List<Country>

    @Query("DELETE FROM country")
    suspend fun deleteCountry()
}