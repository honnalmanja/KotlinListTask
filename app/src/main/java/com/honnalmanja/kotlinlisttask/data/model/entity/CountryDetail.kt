package com.honnalmanja.kotlinlisttask.data.model.entity


import androidx.room.*
import com.google.gson.annotations.SerializedName

@Entity(tableName = "country_detail", foreignKeys = [
    ForeignKey(
        entity = Country::class,
        parentColumns = arrayOf("detail_id"),
        childColumns = arrayOf("country_id"),
        onDelete = ForeignKey.CASCADE
    )
], indices = [Index(value = ["country_id"])])
class CountryDetail(description: String, imageHref: String, title: String, countryID: Int) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "detail_id")
    val aboutID: Int = 0

    @SerializedName("description")
    @ColumnInfo(name = "country_description")
    val description: String = description

    @SerializedName("imageHref")
    @ColumnInfo(name = "image_link")
    val imageHref: String = imageHref

    @SerializedName("title")
    @ColumnInfo(name = "about_title")
    val title: String = title

    @ColumnInfo(name = "country_id")
    val countryID: Int = countryID

}