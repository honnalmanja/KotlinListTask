package com.honnalmanja.kotlinlisttask

import android.app.Application
import com.honnalmanja.kotlinlisttask.di.Injector
import com.honnalmanja.kotlinlisttask.di.core.AppComponent
import com.honnalmanja.kotlinlisttask.di.core.AppModule
import com.honnalmanja.kotlinlisttask.di.core.DaggerAppComponent
import com.honnalmanja.kotlinlisttask.di.core.RetrofitModule
import com.honnalmanja.kotlinlisttask.di.country.CountrySubComponent

class KotlinTask : Application(), Injector{

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(applicationContext))
                .retrofitModule(RetrofitModule(BuildConfig.BASE_URL))
                .build()
    }

    override fun createCountrySubComponent(): CountrySubComponent {
        return appComponent.countrySubComponent().create()
    }
}