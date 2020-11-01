package com.honnalmanja.kotlinlisttask.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.honnalmanja.kotlinlisttask.data.db.dao.CountryDAO
import com.honnalmanja.kotlinlisttask.data.db.dao.CountryDetailDAO
import com.honnalmanja.kotlinlisttask.data.model.entity.Country
import com.honnalmanja.kotlinlisttask.data.model.entity.CountryDetail

@Database(entities = [Country::class, CountryDetail::class], version = 1, exportSchema = false)
abstract class CountryDB: RoomDatabase() {

    abstract fun countryDAO(): CountryDAO

    abstract fun countryDetailDAO(): CountryDetailDAO

}