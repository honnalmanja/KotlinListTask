package com.honnalmanja.kotlinlisttask.di.country

import com.honnalmanja.kotlinlisttask.presentation.CountryActivity
import dagger.Subcomponent

@CountryScope
@Subcomponent(modules = [CountryModule::class])
interface CountrySubComponent {

    fun inject(countryActivity: CountryActivity)

    @Subcomponent.Factory
    interface Factory {
        fun create(): CountrySubComponent
    }

}