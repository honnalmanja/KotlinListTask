package com.honnalmanja.kotlinlisttask.data.model.remote

import com.google.gson.annotations.SerializedName

data class About(

    @SerializedName("description")
    val description: String?,

    @SerializedName("imageHref")
    val imageHref: String?,

    @SerializedName("title")
    val title: String?
)