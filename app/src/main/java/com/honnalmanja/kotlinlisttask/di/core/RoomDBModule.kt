package com.honnalmanja.kotlinlisttask.di.core

import android.content.Context
import androidx.room.Room
import com.honnalmanja.kotlinlisttask.data.db.CountryDB
import com.honnalmanja.kotlinlisttask.data.db.dao.CountryDAO
import com.honnalmanja.kotlinlisttask.data.db.dao.CountryDetailDAO
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomDBModule {

    @Singleton
    @Provides
    fun provideRoomDB(context: Context): CountryDB {
        return Room.databaseBuilder(
                context, CountryDB::class.java, "TaskDB"
        ).build()
    }

    @Singleton
    @Provides
    fun provideCountryDAO(countryDB: CountryDB): CountryDAO{
        return countryDB.countryDAO()
    }

    @Singleton
    @Provides
    fun provideCountryDetailDAO(countryDB: CountryDB): CountryDetailDAO {
        return countryDB.countryDetailDAO()
    }
}