package com.dev.adi.collectapps

import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Before
    fun testBefore () {
        assertEquals(2, 1 + 1)
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @After
    fun testAfter () {
        assertEquals(0, 1 - 1)
    }
}
