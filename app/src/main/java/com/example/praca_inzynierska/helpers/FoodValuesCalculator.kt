package com.example.praca_inzynierska.helpers

import com.example.praca_inzynierska.data.Food

class FoodValuesCalculator {

    fun filterFoodByMeal(meal: String, foods: List<Food>): List<Food> {
        return foods.filter { it.meal == meal }
    }

    fun calculateKcal(meal: String, foods: List<Food>): Int {
        return calculateSumByField(meal, foods) { it.kcal }
    }

    fun calculateFat(meal: String, foods: List<Food>): Int {
        return calculateSumByField(meal, foods) { it.fat }
    }

    fun calculateCarbs(meal: String, foods: List<Food>): Int {
        return calculateSumByField(meal, foods) { it.carbs }
    }

    fun calculateProtein(meal: String, foods: List<Food>): Int {
        return calculateSumByField(meal, foods) { it.proteins }
    }

    private fun calculateSumByField(meal: String, foods: List<Food>, selector: (Food) -> Int): Int {
        return foods
            .filter { it.meal == meal }
            .sumOf(selector)
    }
}