package com.example.praca_inzynierska.validators.diet.configuration

import com.example.praca_inzynierska.diet_configuration.validators.DateOfBirthValidator
import junit.framework.TestCase.assertEquals
import org.junit.Test

class DateOfBirthValidatorTest {

    @Test
    fun `should return success when all fields are valid`() {
        val validator = DateOfBirthValidator("01", "01", "2000")
        val result = validator.validate()
        assertEquals(true, result.successful)
        assertEquals(null, result.errorMessage)
    }

    @Test
    fun `should return error when dayOfBirth is blank`() {
        val validator = DateOfBirthValidator("", "01", "2000")
        val result = validator.validate()
        assertEquals(false, result.successful)
        assertEquals("All fields are required", result.errorMessage)
    }

    @Test
    fun `should return error when monthOfBirth is blank`() {
        val validator = DateOfBirthValidator("01", "", "2000")
        val result = validator.validate()
        assertEquals(false, result.successful)
        assertEquals("All fields are required", result.errorMessage)
    }

    @Test
    fun `should return error when yearOfBirth is blank`() {
        val validator = DateOfBirthValidator("01", "01", "")
        val result = validator.validate()
        assertEquals(false, result.successful)
        assertEquals("All fields are required", result.errorMessage)
    }

    @Test
    fun `should return error when dayOfBirth is not numeric`() {
        val validator = DateOfBirthValidator("abc", "01", "2000")
        val result = validator.validate()
        assertEquals(false, result.successful)
        assertEquals("Enter numeric values to fields", result.errorMessage)
    }

    @Test
    fun `should return error when monthOfBirth is not numeric`() {
        val validator = DateOfBirthValidator("01", "abc", "2000")
        val result = validator.validate()
        assertEquals(false, result.successful)
        assertEquals("Enter numeric values to fields", result.errorMessage)
    }

    @Test
    fun `should return error when yearOfBirth is not numeric`() {
        val validator = DateOfBirthValidator("01", "01", "abc")
        val result = validator.validate()
        assertEquals(false, result.successful)
        assertEquals("Enter numeric values to fields", result.errorMessage)
    }

    @Test
    fun `should return error when date is invalid`() {
        val validator = DateOfBirthValidator("31", "02", "2000")
        val result = validator.validate()
        assertEquals(false, result.successful)
        assertEquals("Invalid date", result.errorMessage)
    }

    @Test
    fun `should return error when user is less than 16 years old`() {
        val validator = DateOfBirthValidator("01", "01", "2010")
        val result = validator.validate()
        assertEquals(false, result.successful)
        assertEquals("You must be at least 16 years old", result.errorMessage)
    }

    @Test
    fun `should return success when user is 100 years old`() {
        val validator = DateOfBirthValidator("01", "01", "1955")
        val result = validator.validate()
        assertEquals(true, result.successful)
        assertEquals(null, result.errorMessage)
    }

    @Test
    fun `should return error when user is older than 100 years`() {
        val validator = DateOfBirthValidator("01", "01", "1910")
        val result = validator.validate()
        assertEquals(false, result.successful)
        assertEquals("You cannot be older than 100 years", result.errorMessage)
    }

}