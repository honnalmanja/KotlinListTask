package com.honnalmanja.kotlinlisttask.data.model.entity


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "country")
class Country(title: String) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "country_id")
    var countryID: Int = 1

    @ColumnInfo(name = "country_name")
    var title: String = title
}