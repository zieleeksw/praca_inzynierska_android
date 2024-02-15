package com.example.praca_inzynierska.training.service

import com.example.praca_inzynierska.commons.services.Retrofit
import com.example.praca_inzynierska.training.data.Exercise
import com.example.praca_inzynierska.training.requests.ExerciseRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path


val exercisesService: ExercisesService = Retrofit.retrofit.create(ExercisesService::class.java)

interface ExercisesService {

    @GET("/api/exercise/available")
    suspend fun fetchAvailableExercises(
        @Header("Authorization") authorization: String
    ): List<String>

    @GET("/api/exercise/user/{id}/date/{date}/name/{name}")
    suspend fun fetchUserExercisesByDateAndName(
        @Header("Authorization") authorization: String,
        @Path("id") userId: Long,
        @Path("date") date: String,
        @Path("name") name: String
    ): List<Exercise>

    @GET("/api/exercise/user/{id}/date/{date}")
    suspend fun fetchUserExercisesByDate(
        @Header("Authorization") authorization: String,
        @Path("id") userId: Long,
        @Path("date") date: String
    ): List<Exercise>


    @POST("/api/exercise")
    suspend fun addExercise(
        @Header("Authorization") authorization: String,
        @Body exerciseRequest: ExerciseRequest
    ): Response<Void>

    @DELETE("/api/exercise/{exerciseId}")
    suspend fun deleteExercise(
        @Header("Authorization") authorization: String,
        @Path("exerciseId") exerciseId: Long
    ): Response<Void>

    @DELETE("/api/exercise/date/{date}/name/{name}")
    suspend fun deleteExercisesByDateAndName(
        @Header("Authorization") authorization: String,
        @Path("date") date: String,
        @Path("name") name: String
    ): Response<Void>
}