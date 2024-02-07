package com.example.praca_inzynierska.service

import com.example.praca_inzynierska.data.training.BaseAppExercises
import com.example.praca_inzynierska.data.training.Exercise
import com.example.praca_inzynierska.requests.ExerciseRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path


val exercisesApiService: ExercisesApiService =
    Retrofit.retrofit.create(ExercisesApiService::class.java)

interface ExercisesApiService {

    @GET("/api/base_app_exercises")
    suspend fun fetchAllBaseAppExercises(
        @Header("Authorization") authorization: String
    ): List<BaseAppExercises>

    @GET("/api/exercise/user/{id}/date/{date}/name/{name}")
    suspend fun fetchExercisesByDateAndName(
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