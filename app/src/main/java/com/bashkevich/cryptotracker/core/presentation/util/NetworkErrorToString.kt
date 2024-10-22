package com.bashkevich.cryptotracker.core.presentation.util

import android.content.Context
import com.bashkevich.cryptotracker.R
import com.bashkevich.cryptotracker.core.domain.util.NetworkError
import com.bashkevich.cryptotracker.core.domain.util.NetworkError.*

fun NetworkError.toString(context: Context) : String {
    val resId = when (this) {
        REQUEST_TIMEOUT -> R.string.error_request_timeout
        TOO_MANY_REQUESTS ->  R.string.error_too_many_requests
        NO_INTERNET -> R.string.error_no_internet
        SERVER_ERROR -> R.string.error_unknown
        SERIALIZATION -> R.string.error_serialization
        UNKNOWN -> R.string.error_unknown
    }

    return context.getString(resId)
}