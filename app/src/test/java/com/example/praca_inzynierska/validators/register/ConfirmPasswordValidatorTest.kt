package com.example.praca_inzynierska.validators.register

import com.example.praca_inzynierska.auth.validators.ConfirmPasswordValidator
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import org.junit.Test

class ConfirmPasswordValidatorTest {

    @Test
    fun `should return success for matching passwords`() {
        val password = "MatchingPwd123!"
        val confirmPassword = "MatchingPwd123!"
        val validator = ConfirmPasswordValidator(password, confirmPassword)
        val result = validator.validate()
        assertEquals(true, result.successful)
        assertNull(result.errorMessage, null)
    }

    @Test
    fun `should return error for non-matching passwords`() {
        val password = "Pwd123!"
        val confirmPassword = "NonMatchingPwd123!"
        val validator = ConfirmPasswordValidator(password, confirmPassword)
        val result = validator.validate()
        assertEquals(false, result.successful)
        assertEquals("The passwords don't match", result.errorMessage)
    }
}