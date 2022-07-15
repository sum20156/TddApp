package com.example.tddapp

import com.example.tddapp.ui.main_act.MainActValidator
import org.junit.Test
import org.junit.Assert.*


internal class MainActivityValidatorTest {


    @Test
    fun `should show error for empty name`() {
        val name =""
        assertEquals(false,MainActValidator.validateName(name))
    }

    @Test
    fun `should show error for bigger name`() {
        val name ="asdassda asdas asdasqwed asd q wqedwqe asdas asds"
        assertEquals(false,MainActValidator.validateName(name))
    }

    @Test
    fun `should show success for valid name`() {
        val name ="suman mondal"
        assertEquals(true,MainActValidator.validateName(name))
    }

    @Test
    fun `should show error for empty email`() {
        val email =""
        assertEquals(false,MainActValidator.validateEmail(email))
    }

    @Test
    fun `should show error for invalid email`() {
        val email ="test_email"
        assertEquals(false,MainActValidator.validateEmail(email))
    }

    @Test
    fun `should show success for valid email`() {
        val email ="test@mail.com"
        assertEquals(true,MainActValidator.validateEmail(email))
    }

}