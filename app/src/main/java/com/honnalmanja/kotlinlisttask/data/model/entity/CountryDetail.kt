package com.honnalmanja.kotlinlisttask.data.model.entity


import androidx.room.*
import com.google.gson.annotations.SerializedName

@Entity(tableName = "country_detail", foreignKeys = [
    ForeignKey(
        entity = Country::class,
        parentColumns = arrayOf("country_id"),
        childColumns = arrayOf("country_id"),
        onDelete = ForeignKey.CASCADE
    )
], indices = [Index(value = ["country_id"])]
)
class CountryDetail(description: String?, imageHref: String?, title: String?, countryID: Int) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "detail_id")
    var detailID: Int = 0

    @ColumnInfo(name = "country_description")
    var description: String? = description

    @ColumnInfo(name = "image_link")
    var imageHref: String? = imageHref

    @ColumnInfo(name = "about_title")
    var title: String? = title

    @ColumnInfo(name = "country_id")
    var countryID: Int = countryID



}