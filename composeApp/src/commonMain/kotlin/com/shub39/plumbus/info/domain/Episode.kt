package com.shub39.plumbus.info.domain

// episode data class
data class Episode(
    val id: Int,
    val name: String,
    val airDate: String,
    val episode: String,
    val characters: List<String>,
    val url: String,
    val isFav: Boolean
)
