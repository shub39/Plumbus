package com.shub39.plumbus.core.presentation

import com.shub39.plumbus.core.domain.DataError
import plumbus.composeapp.generated.resources.Res
import plumbus.composeapp.generated.resources.disk_full
import plumbus.composeapp.generated.resources.no_internet
import plumbus.composeapp.generated.resources.no_results
import plumbus.composeapp.generated.resources.server_error
import plumbus.composeapp.generated.resources.too_many_requests
import plumbus.composeapp.generated.resources.unknown

fun DataError.toUiText(): UiText {
    val stringRes = when(this) {
        DataError.Local.DISK_FULL -> Res.string.disk_full
        DataError.Local.UNKNOWN -> Res.string.unknown
        DataError.Remote.REQUEST_TIMEOUT -> Res.string.too_many_requests
        DataError.Remote.TOO_MANY_REQUESTS -> Res.string.too_many_requests
        DataError.Remote.NO_INTERNET -> Res.string.no_internet
        DataError.Remote.SERVER -> Res.string.server_error
        DataError.Remote.SERIALIZATION -> Res.string.unknown
        DataError.Remote.UNKNOWN -> Res.string.unknown
        DataError.Remote.NO_RESULTS -> Res.string.no_results
    }

    return UiText.StringResourceId(stringRes)
}