package com.honnalmanja.kotlinlisttask.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.honnalmanja.kotlinlisttask.data.model.remote.Canada
import com.honnalmanja.kotlinlisttask.domain.CountryUseCase

class CountryViewModel(private val countryUseCase: CountryUseCase): ViewModel() {


    fun getCountry() = liveData<Canada?> {
        val country = countryUseCase.getCountryWithDetails()
        emit(country)
    }

    fun refreshCountry() = liveData<Canada?> {
        val country = countryUseCase.refreshCountryWithDetails()
        emit(country)
    }

}