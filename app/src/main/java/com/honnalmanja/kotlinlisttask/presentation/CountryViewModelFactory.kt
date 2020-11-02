package com.honnalmanja.kotlinlisttask.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.honnalmanja.kotlinlisttask.domain.CountryUseCase

class CountryViewModelFactory(
        private val countryUseCase: CountryUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CountryViewModel(countryUseCase) as T
    }
}