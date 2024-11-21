package com.shub39.plumbus.core.domain

sealed interface DataError: Error {
    enum class Remote: DataError {
        REQUEST_TIMEOUT,
        TOO_MANY_REQUESTS,
        NO_INTERNET,
        SERVER,
        SERIALIZATION,
        UNKNOWN,
        NO_RESULTS
    }

    enum class Local: DataError {
        DISK_FULL,
        UNKNOWN
    }
}