package com.example.tddapp

import android.graphics.BitmapFactory
import com.example.tddapp.ui.main_act.MainActValidator
import com.example.tddapp.ui.main_act.MainActivityViewModel
import org.junit.Test
import org.junit.Assert.*

class MainActivityValidatorTest {


    @Test
    fun validBitmap(){
        val stream = this.javaClass.classLoader?.getResourceAsStream("test_success.jpg")
        val bitmap =BitmapFactory.decodeStream(stream)
        assertEquals(true,MainActValidator.validateBitmap(bitmap))
    }
}