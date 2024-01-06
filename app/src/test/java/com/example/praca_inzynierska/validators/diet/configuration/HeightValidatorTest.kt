package com.example.praca_inzynierska.validators.diet.configuration

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertNull
import junit.framework.TestCase.assertTrue
import org.junit.Test


class HeightValidatorTest {

    @Test
    fun `validate height - correct input`() {
        val validator = HeightValidator("123")
        val result = validator.validate()
        assertTrue(result.successful)
        assertNull(result.errorMessage)
    }

    @Test
    fun `validate height - blank input`() {
        val validator = HeightValidator("")
        val result = validator.validate()
        assertFalse(result.successful)
        assertEquals("You have to enter height", result.errorMessage)
    }

    @Test
    fun `validate height - non-numeric input`() {
        val validator = HeightValidator("abc")
        val result = validator.validate()
        assertFalse(result.successful)
        assertEquals("Height must contain only numeric digits", result.errorMessage)
    }

    @Test
    fun `validate height - invalid length`() {
        val validator = HeightValidator("12")
        val result = validator.validate()
        assertFalse(result.successful)
        assertEquals("Height must be three digits long", result.errorMessage)
    }
}