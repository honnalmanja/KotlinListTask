package com.honnalmanja.kotlinlisttask.domain

import com.honnalmanja.kotlinlisttask.data.model.remote.Canada

interface CountryRepository {

    suspend fun getCountryWithDetails() : Canada?

    suspend fun updateCountryWithDetails() : Canada?

}