package com.honnalmanja.kotlinlisttask.domain

import com.honnalmanja.kotlinlisttask.data.model.remote.Canada

class CountryUseCase(private val countryRepository: CountryRepository) {

    suspend fun getCountryWithDetails() : Canada?
            = countryRepository.getCountryWithDetails()

    suspend fun refreshCountryWithDetails() : Canada?
            = countryRepository.updateCountryWithDetails()

}