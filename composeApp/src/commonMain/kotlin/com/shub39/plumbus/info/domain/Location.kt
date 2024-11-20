package com.shub39.plumbus.info.domain

// location data class
data class Location(
    val url: String,
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<String>
)
