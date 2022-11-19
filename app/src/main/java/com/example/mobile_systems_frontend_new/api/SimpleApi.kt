package com.example.mobile_systems_frontend_new.api

import com.example.mobile_systems_frontend_new.model.Post
import okhttp3.Response
import retrofit2.http.GET

interface SimpleApi {

    @GET("posts/1")
    suspend fun getPost(): Post
}