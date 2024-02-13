package com.example.praca_inzynierska.validators.register

import com.example.praca_inzynierska.auth.validators.EmailValidator
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import org.junit.Test

class EmailValidatorTest {

    @Test
    fun `should return success for valid email`() {
        val validator = EmailValidator("valid@email.com")
        val result = validator.validate()
        assertEquals(true, result.successful)
        assertNull(result.errorMessage, null)
    }

    @Test
    fun `should return error when email is blank`() {
        val validator = EmailValidator("")
        val result = validator.validate()
        assertEquals(false, result.successful)
        assertEquals("The email can't be Blank", result.errorMessage)
    }

    @Test
    fun `should return error for an invalid email format`() {
        val validator = EmailValidator("invalid-email")
        val result = validator.validate()
        assertEquals(false, result.successful)
        assertEquals("That's not a valid email", result.errorMessage)
    }
}