package com.honnalmanja.kotlinlisttask.data.model.entity


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "country")
data class Country(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "country_id")
    val countryID: Int = 0,

    @Ignore
    @SerializedName("rows")
    val countryDetails: List<CountryDetail>,

    @ColumnInfo(name = "country_name")
    @SerializedName("title")
    val title: String
)