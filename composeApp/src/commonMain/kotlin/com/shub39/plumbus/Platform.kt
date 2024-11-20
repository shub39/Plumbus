package com.shub39.plumbus

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform