package com.honnalmanja.kotlinlisttask.data.model.remote

import com.google.gson.annotations.SerializedName

data class Canada(

    @SerializedName("rows")
    var abouts: List<About>,

    @SerializedName("title")
    var title: String?
)