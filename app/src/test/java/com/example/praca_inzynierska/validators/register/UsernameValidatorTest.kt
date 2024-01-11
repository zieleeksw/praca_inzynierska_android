package com.example.praca_inzynierska.validators.register

import com.example.praca_inzynierska.validators.login.register.UsernameValidator
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import org.junit.Test

class UsernameValidatorTest {
    @Test
    fun `should return success for valid username`() {
        val validator = UsernameValidator("ValidUser123")
        val result = validator.validate()
        assertEquals(true, result.successful)
        assertNull(result.errorMessage, null)
    }

    @Test
    fun `should return error when username is too short`() {
        val validator = UsernameValidator("Short")
        val result = validator.validate()
        assertEquals(false, result.successful)
        assertEquals("The username must be at least 6 characters long", result.errorMessage)
    }

    @Test
    fun `should return error when username contains invalid characters`() {
        val validator = UsernameValidator("Invalid@User")
        val result = validator.validate()
        assertEquals(false, result.successful)
        assertEquals("The username must contain only letters and numbers", result.errorMessage)
    }

    @Test
    fun `should return error for an empty username`() {
        val validator = UsernameValidator("")
        val result = validator.validate()
        assertEquals(false, result.successful)
        assertEquals("The username must be at least 6 characters long", result.errorMessage)
    }

    @Test
    fun `should return error for a username with whitespace`() {
        val validator = UsernameValidator("User Name")
        val result = validator.validate()
        assertEquals(false, result.successful)
        assertEquals("The username must contain only letters and numbers", result.errorMessage)
    }
}
