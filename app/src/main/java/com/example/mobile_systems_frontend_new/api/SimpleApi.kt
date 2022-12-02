package com.example.mobile_systems_frontend_new.api

import com.example.mobile_systems_frontend_new.model.CalculationResponse
import com.example.mobile_systems_frontend_new.model.Post
import com.example.mobile_systems_frontend_new.model.Map
import com.example.mobile_systems_frontend_new.model.PostUserData
import okhttp3.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface SimpleApi {

    @GET("/")
    suspend fun getPost(): Map

    @POST("/")
    suspend fun calculateLocation(
        @Body postUserData: PostUserData
    ): CalculationResponse
}