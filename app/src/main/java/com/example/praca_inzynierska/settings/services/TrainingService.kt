package com.example.praca_inzynierska.settings.services

import com.example.praca_inzynierska.commons.services.Retrofit
import com.example.praca_inzynierska.settings.data.Training
import com.example.praca_inzynierska.settings.requests.ExerciseToTrainingRequest
import com.example.praca_inzynierska.settings.requests.TrainingRequest
import com.example.praca_inzynierska.training.data.Exercise
import com.example.praca_inzynierska.training.requests.AddTrainingBlockRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

val trainingService: TrainingService = Retrofit.retrofit.create(TrainingService::class.java)

interface TrainingService {

    @GET("/api/user/{userId}/trainings")
    suspend fun fetchTrainings(
        @Header("Authorization") authorization: String,
        @Path("userId") userId: Long,
    ): List<Training>

    @POST("/api/user/trainings")
    suspend fun addTraining(
        @Header("Authorization") authorization: String,
        @Body trainingRequest: TrainingRequest
    ): Response<Boolean>

    @DELETE("/api/user/trainings/{trainingId}")
    suspend fun deleteTraining(
        @Header("Authorization") authorization: String,
        @Path("trainingId") trainingId: Long
    ): Response<Void>

    @GET("/api/user/trainings/{trainingId}")
    suspend fun fetchTrainingById(
        @Header("Authorization") authorization: String,
        @Path("trainingId") trainingId: Long
    ): Response<Training>

    @DELETE("/api/user/trainings/exercise/{exerciseId}")
    suspend fun deleteExerciseFromTraining(
        @Header("Authorization") authorization: String,
        @Path("exerciseId") exerciseId: Long
    ): Response<Void>

    @DELETE("/api/user/trainings/{trainingId}/exercise/{exerciseName}")
    suspend fun deleteExerciseFromTrainingByName(
        @Header("Authorization") authorization: String,
        @Path("trainingId") trainingId: Long,
        @Path("exerciseName") exerciseName: String
    ): Response<Void>

    @GET("/api/user/trainings/{trainingId}/{exerciseName}")
    suspend fun fetchExercisesByTrainingIdAndName(
        @Header("Authorization") authorization: String,
        @Path("trainingId") trainingId: Long,
        @Path("exerciseName") name: String
    ): List<Exercise>

    @POST("/api/user/trainings/exercise")
    suspend fun addExerciseToTraining(
        @Header("Authorization") authorization: String,
        @Body exerciseToTrainingRequest: ExerciseToTrainingRequest
    ): Response<Void>

    @POST("/api/user/trainings/block")
    suspend fun addTrainingToDay(
        @Header("Authorization") authorization: String,
        @Body addTrainingBlockRequest: AddTrainingBlockRequest
    ): Response<Void>
}