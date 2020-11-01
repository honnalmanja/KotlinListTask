package com.honnalmanja.kotlinlisttask.data.model.remote

import com.google.gson.annotations.SerializedName

data class Canada(

    @SerializedName("rows")
    val abouts: List<About>,

    @SerializedName("title")
    val title: String
)