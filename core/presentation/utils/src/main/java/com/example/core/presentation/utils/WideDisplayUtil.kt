package com.example.core.presentation.utils

import android.app.Activity
import androidx.activity.compose.LocalActivity
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember


@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun isWideDisplay(activity: Activity = LocalActivity.current as Activity): Boolean {
    val windowWidthSizeClass = calculateWindowSizeClass(activity = activity)
    val isVIdeDisplay: Boolean by remember {
        derivedStateOf {
            windowWidthSizeClass.widthSizeClass >= WindowWidthSizeClass.Medium
        }
    }

    return isVIdeDisplay
}