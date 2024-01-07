package com.example.praca_inzynierska

data class UserRegisterRequest(
    private val username: String,
    private val email: String,
    private val password: String
)

data class User(
    val id: Long,
    val username: String,
    val email: String,
    val token: String,
    val userNutritionConfig: UserNutritionConfig?
)

data class UserNutritionConfigRequest(
    val gender: String,
    val dob: String,
    val activityLevel: String,
    val height: Int,
    val currentWeight: Double,
    val targetWeight: Double,
)


data class UserNutritionConfig(
    val gender: String,
    val dob: String,
    val activityLevel: String,
    val height: Int,
    val currentWeight: Double,
    val targetWeight: Double,
    val dietStatus: String,
    val caloricNeeds: Int,
    val carbNeeds: Int,
    val fatNeeds: Int,
    val proteinNeeds: Int,
    val dietFinish: String
)
