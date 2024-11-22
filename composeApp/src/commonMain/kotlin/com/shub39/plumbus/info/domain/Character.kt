package com.shub39.plumbus.info.domain

// character data class
data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val type: String,
    val species: String,
    val gender: String,
    val imageUrl: String,
    val episodes: List<String>,
    val url: String,
    val origin: Pair<String, String>,
    val location: Pair<String, String>,
    val isFav: Boolean
)