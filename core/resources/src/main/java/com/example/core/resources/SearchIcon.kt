package com.example.core.resources


import androidx.compose.foundation.Image
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

val SearchIcon: ImageVector
    @Composable
    get() {
        val onBackground = MaterialTheme.colorScheme.onBackground

        _SearchIcon = remember(onBackground) {
            ImageVector.Builder(
                name = "SearchIcon",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 16f,
                viewportHeight = 16f
            ).apply {
                path(
                    fill = SolidColor(onBackground),
                    pathFillType = PathFillType.EvenOdd
                ) {
                    moveTo(12.027f, 9.92f)
                    lineTo(16f, 13.95f)
                    lineTo(14f, 16f)
                    lineToRelative(-4.075f, -3.976f)
                    arcTo(
                        6.465f,
                        6.465f,
                        0f,
                        isMoreThanHalf = false,
                        isPositiveArc = true,
                        6.5f,
                        13f
                    )
                    curveTo(2.91f, 13f, 0f, 10.083f, 0f, 6.5f)
                    curveTo(0f, 2.91f, 2.917f, 0f, 6.5f, 0f)
                    curveTo(10.09f, 0f, 13f, 2.917f, 13f, 6.5f)
                    arcToRelative(
                        6.463f,
                        6.463f,
                        0f,
                        isMoreThanHalf = false,
                        isPositiveArc = true,
                        -0.973f,
                        3.42f
                    )
                    close()
                    moveTo(1.997f, 6.452f)
                    curveToRelative(0f, 2.48f, 2.014f, 4.5f, 4.5f, 4.5f)
                    curveToRelative(2.48f, 0f, 4.5f, -2.015f, 4.5f, -4.5f)
                    curveToRelative(0f, -2.48f, -2.015f, -4.5f, -4.5f, -4.5f)
                    curveToRelative(-2.48f, 0f, -4.5f, 2.014f, -4.5f, 4.5f)
                    close()
                }
            }.build()
        }

        return _SearchIcon!!
    }

@Suppress("ObjectPropertyName")
private var _SearchIcon: ImageVector? = null


@Preview
@Composable
private fun ShowImg() {
    MaterialTheme {
        Image(
            imageVector = SearchIcon,
            contentDescription = null,
        )
    }
}