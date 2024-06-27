package ru.ifmo.commons
import ru.ifmo.commons.dto.ApiResponse
import kotlin.test.Test
import kotlin.test.assertEquals

class SampleTest {
    private val testSample: ApiResponse = ApiResponse()

    @Test
    fun testSum() {
        testSample.message = "123";
        testSample.success = true;
        assertEquals(testSample.message, "123")
        assertEquals(testSample.success, true)
    }
}