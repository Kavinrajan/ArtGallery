package com.com.kavin.artgallery.data.remote

import com.kavin.artgallery.data.remote.ApiResponse
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Response

@RunWith(JUnit4::class)
class ApiResponseTest {

    @Before
    fun setUp() {

    }

    @After
    fun tearDown() {

    }

    @Test
    fun `test message is not null or empty in Exception response`() {
        val exception = Exception("message")
        val apiResponse = ApiResponse.exception<String>(exception)
        MatcherAssert.assertThat(apiResponse.message, CoreMatchers.`is`("Message"))
    }

    @Test
    fun `test data is not null in Success response`() {
        val apiResponse = ApiResponse.create(200 .. 299, Response.success("test_body") )
        if(apiResponse is ApiResponse.ApiSuccessResponse) {
            MatcherAssert.assertThat(apiResponse.data, CoreMatchers.notNullValue())
            MatcherAssert.assertThat(apiResponse.data, CoreMatchers.`is`("test_body"))
        }
    }

}