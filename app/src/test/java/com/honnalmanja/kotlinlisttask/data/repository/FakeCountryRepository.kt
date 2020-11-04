package com.honnalmanja.kotlinlisttask.data.repository

import com.honnalmanja.kotlinlisttask.data.model.remote.About
import com.honnalmanja.kotlinlisttask.data.model.remote.Canada
import com.honnalmanja.kotlinlisttask.domain.CountryRepository

class FakeCountryRepository: CountryRepository {

    private var canada: Canada?

    init {
        val aboutList: List<About> = mutableListOf(
                About("Description 1","Image 1","Title 1"),
                About("Description 2","Image 2","Title 2"),
                About("Description 3","Image 3","Title 3"),

        )
        canada = Canada(aboutList, "Canada")
    }

    override suspend fun getCountryWithDetails(): Canada? {
        return canada
    }

    override suspend fun updateCountryWithDetails(): Canada? {

        val aboutList: List<About> = mutableListOf(
                About("Description 4","Image 4","Title 4"),
                About("Description 5","Image 5","Title 5")
        )
        canada = Canada(aboutList, "Canada")
        return canada
    }
}