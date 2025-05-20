package com.example.core.data.network.models

import com.example.core.domain.models.ErrorType

@JvmInline
value class StatusCode(val code: Int)

fun StatusCode.mapToErrorType(): ErrorType {
    return when (code) {
        NetworkParams.NO_CONNECTION_CODE -> ErrorType.NO_CONNECTION
        NetworkParams.BAD_REQUEST_CODE -> ErrorType.BAD_REQUEST
        NetworkParams.NOT_FOUND_CODE -> ErrorType.NOTHING_FOUND
        NetworkParams.SERVER_ERROR_CODE -> ErrorType.SERVER_ERROR
        else -> ErrorType.UNKNOWN_ERROR
    }
}