package com.example.praca_inzynierska.validators.register

import com.example.praca_inzynierska.validators.login.register.PasswordValidator
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import org.junit.Test

class PasswordValidatorTest {

    @Test
    fun `should return success for valid password`() {
        val validator = PasswordValidator("ValidP@ssw0rd")
        val result = validator.validate()
        assertEquals(true, result.successful)
        assertNull(result.errorMessage, null)
    }

    @Test
    fun `should return error when password is too short`() {
        val validator = PasswordValidator("ShortPw")
        val result = validator.validate()
        assertEquals(false, result.successful)
        assertEquals("The password needs to consist at least 8 characters", result.errorMessage)
    }

    @Test
    fun `should return error for a password without uppercase letter`() {
        val validator = PasswordValidator("nopassword123!")
        val result = validator.validate()
        assertEquals(false, result.successful)
        assertEquals("The password must contain at least one uppercase letter", result.errorMessage)
    }

    @Test
    fun `should return error for a password without lowercase letter`() {
        val validator = PasswordValidator("NOPASSWORD123!")
        val result = validator.validate()
        assertEquals(false, result.successful)
        assertEquals("The password must contain at least one lowercase letter", result.errorMessage)
    }

    @Test
    fun `should return error for a password without digit`() {
        val validator = PasswordValidator("NoPassword!")
        val result = validator.validate()
        assertEquals(false, result.successful)
        assertEquals("The password must contain at least one digit", result.errorMessage)
    }

    @Test
    fun `should return error for a password without special character`() {
        val validator = PasswordValidator("NoPassword123")
        val result = validator.validate()
        assertEquals(false, result.successful)
        assertEquals(
            "The password must contain at least one special character",
            result.errorMessage
        )
    }
}