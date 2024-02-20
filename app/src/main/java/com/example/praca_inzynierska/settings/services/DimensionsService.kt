package com.example.praca_inzynierska.settings.services

import com.example.praca_inzynierska.commons.services.Retrofit
import com.example.praca_inzynierska.settings.data.BodyDimensions
import com.example.praca_inzynierska.settings.requests.DimensionsRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

val dimensionsService: DimensionsService = Retrofit.retrofit.create(DimensionsService::class.java)

interface DimensionsService {

    @GET("/api/dimensions/{userId}")
    suspend fun fetchDimensions(
        @Header("Authorization") authorization: String,
        @Path("userId") userId: Long
    ): List<BodyDimensions>

    @DELETE("/api/dimensions/{dimensionsId}")
    suspend fun deleteDimensions(
        @Header("Authorization") authorization: String,
        @Path("dimensionsId") dimensionsId: Long
    ): Response<Void>

    @POST("api/dimensions")
    suspend fun addDimensions(
        @Header("Authorization") authorization: String,
        @Body dimensionsRequest: DimensionsRequest
    ): Response<Void>
}