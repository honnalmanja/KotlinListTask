package com.honnalmanja.kotlinlisttask.di

import com.honnalmanja.kotlinlisttask.di.country.CountrySubComponent

interface Injector {

    fun createCountrySubComponent(): CountrySubComponent

}