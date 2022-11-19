package com.example.mobile_systems_frontend_new.api

import com.example.mobile_systems_frontend_new.model.Post
import com.example.mobile_systems_frontend_new.model.Map
import okhttp3.Response
import retrofit2.http.GET

interface SimpleApi {

    @GET("/")
    suspend fun getPost(): Map
}