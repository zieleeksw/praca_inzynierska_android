package com.example.praca_inzynierska.validators.diet.configuration

import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals

class WeightValidatorTest {

    @Test
    fun `should return success for two-digit whole number weight`() {
        val validator = WeightValidator("85")
        val result = validator.validate()
        assertEquals(true, result.successful)
        assertEquals(null, result.errorMessage)
    }

    @Test
    fun `should return success for two-digit decimal number weight with dot separator`() {
        val validator = WeightValidator("76.7")
        val result = validator.validate()
        assertEquals(true, result.successful)
        assertEquals(null, result.errorMessage)
    }

    @Test
    fun `should return success for two-digit decimal number weight with comma separator`() {
        val validator = WeightValidator("67,8")
        val result = validator.validate()
        assertEquals(true, result.successful)
        assertEquals(null, result.errorMessage)
    }

    @Test
    fun `should return error for invalid weight format`() {
        val validator = WeightValidator("abc")
        val result = validator.validate()
        assertEquals(false, result.successful)
        assertEquals("Invalid weight format", result.errorMessage)
    }

    @Test
    fun `should return error for weight with one number before dot separator`() {
        val validator = WeightValidator("7.77")
        val result = validator.validate()
        assertEquals(false, result.successful)
        assertEquals("Invalid weight format", result.errorMessage)
    }

    @Test
    fun `should return error for weight with one number before  comma separator and nothing next`() {
        val validator = WeightValidator("6,")
        val result = validator.validate()
        assertEquals(false, result.successful)
        assertEquals("Invalid weight format", result.errorMessage)
    }

    @Test
    fun `should return error for weight with all dots and commas`() {
        val validator = WeightValidator("..,,")
        val result = validator.validate()
        assertEquals(false, result.successful)
        assertEquals("Invalid weight format", result.errorMessage)
    }
}