package com.example.praca_inzynierska.settings.services

import com.example.praca_inzynierska.commons.services.Retrofit
import com.example.praca_inzynierska.training.data.Exercise
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

val exercisesChartService: ExercisesChartService =
    Retrofit.retrofit.create(ExercisesChartService::class.java)

interface ExercisesChartService {

    @GET("/api/exercise/user/{userId}/chart/{date}/{name}")
    suspend fun fetchExercisesByYearMonthAndName(
        @Header("Authorization") authorization: String,
        @Path("userId") trainingId: Long,
        @Path("date") date: String,
        @Path("name") name: String,
    ): List<Exercise>
}