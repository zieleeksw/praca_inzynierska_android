package com.example.praca_inzynierska.settings.services

import com.example.praca_inzynierska.commons.services.Retrofit
import com.example.praca_inzynierska.settings.data.UserExercise
import com.example.praca_inzynierska.settings.requests.UserExerciseRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

val userExercisesService: UserExercisesService =
    Retrofit.retrofit.create(UserExercisesService::class.java)

interface UserExercisesService {

    @GET("/api/user/{userId}/exercises")
    suspend fun fetchUserExercises(
        @Header("Authorization") authorization: String,
        @Path("userId") userId: Long,
    ): List<UserExercise>

    @POST("/api/user/{userId}/exercises")
    suspend fun addUserExercise(
        @Header("Authorization") authorization: String,
        @Body userExerciseRequest: UserExerciseRequest
    ): Response<Boolean>

    @DELETE("/api/user/{userId}/exercises")
    suspend fun deleteUserExercise(
        @Header("Authorization") authorization: String,
        @Path("userId") userId: Long
    ): Response<Void>
}