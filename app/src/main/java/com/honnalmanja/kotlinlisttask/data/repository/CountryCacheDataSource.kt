package com.honnalmanja.kotlinlisttask.data.repository

import com.honnalmanja.kotlinlisttask.data.model.remote.Canada

interface CountryCacheDataSource {

    suspend fun getCountryWithDetails(): Canada

}