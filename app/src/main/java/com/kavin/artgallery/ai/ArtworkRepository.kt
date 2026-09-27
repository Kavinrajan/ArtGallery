package com.kavin.artgallery.domain

interface ArtworkRepository {
    suspend fun getAllArtworks(): List<Artwork>
}
