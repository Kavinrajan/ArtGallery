package com.kavin.artgallery.model

import com.google.gson.Gson
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class PhotoModelTest {

    @Test
    fun `test PhotoModel hashCode does not throw NPE when fields are null`() {
        val photo = PhotoModel(
            id = null,
            created_at = null,
            updatedAt = null,
            promotedAt = null,
            width = null,
            height = null,
            color = null,
            blurHash = null,
            description = null,
            altDescription = null,
            urls = null,
            links = null,
            likes = null,
            likedByUser = null,
            currentUserCollections = null,
            sponsorship = null,
            user = null,
            tags = null
        )

        // Calling hashCode() should not throw NullPointerException
        val hashCode = photo.hashCode()
        assertThat(hashCode, `is`(notNullValue()))
    }

    @Test
    fun `test Sponsorship hashCode does not throw NPE when impressionUrls is null`() {
        val sponsorship = Sponsorship(
            impressionUrls = null,
            tagline = null,
            taglineUrl = null,
            sponsor = null
        )
        val hashCode = sponsorship.hashCode()
        assertThat(hashCode, `is`(notNullValue()))
    }

    @Test
    fun `test PhotoModel deserialization from JSON with null collections does not crash on hashCode`() {
        val json = """
            {
                "id": "123",
                "created_at": "2021-01-01T00:00:00Z"
            }
        """.trimIndent()

        val gson = Gson()
        val photo = gson.fromJson(json, PhotoModel::class.java)

        assertThat(photo, `is`(notNullValue()))
        // Calling hashCode on deserialized photo object where missing fields are null
        val hashCode = photo.hashCode()
        assertThat(hashCode, `is`(notNullValue()))
    }
}
