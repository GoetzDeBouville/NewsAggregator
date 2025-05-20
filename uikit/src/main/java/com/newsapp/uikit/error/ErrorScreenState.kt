package com.newsapp.uikit.error

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.core.resources.NothingFoundImg
import com.example.core.resources.R
import com.example.core.resources.SomethingWentWrongImg

enum class ErrorScreenState(
    val errorImg: @Composable () -> ImageVector,
    val errorDescriptionResId: Int
) {
    NO_INTERNET(
        { SomethingWentWrongImg },
        R.string.error_no_internet_connection
    ),
    SERVER_ERROR(
        { SomethingWentWrongImg },
        R.string.error_something_went_wrong
    ),
    NOTHING_FOUND(
        { NothingFoundImg },
        R.string.error_nothing_found
    )
}
