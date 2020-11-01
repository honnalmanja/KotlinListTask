package com.honnalmanja.kotlinlisttask.data.api

import com.honnalmanja.kotlinlisttask.data.model.remote.Canada
import retrofit2.http.GET

interface TaskAPI {

    @GET("/2iodh4vg0eortkl/facts.json")
    suspend fun getAPIData() : Canada

}