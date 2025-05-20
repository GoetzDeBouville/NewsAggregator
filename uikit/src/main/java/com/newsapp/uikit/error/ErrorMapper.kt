package com.newsapp.uikit.error

import com.example.core.domain.models.ErrorType

fun ErrorType.mapToErrorScreen(): ErrorScreenState {
    return when (this) {
        ErrorType.NO_CONNECTION -> ErrorScreenState.NO_INTERNET
        ErrorType.BAD_REQUEST -> ErrorScreenState.NOTHING_FOUND
        ErrorType.SERVER_ERROR -> ErrorScreenState.SERVER_ERROR
        ErrorType.NOTHING_FOUND -> ErrorScreenState.NOTHING_FOUND
        ErrorType.UNKNOWN_ERROR -> ErrorScreenState.SERVER_ERROR
    }
}