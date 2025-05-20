package com.example.core.resources

import androidx.compose.foundation.Image
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

val NothingFoundImg: ImageVector
    @Composable
    get() {
        val onBackground = MaterialTheme.colorScheme.onBackground

        _NothingFoundImg = remember(onBackground) {
            ImageVector.Builder(
                name = "NothingFound",
                defaultWidth = 240.dp,
                defaultHeight = 240.dp,
                viewportWidth = 60f,
                viewportHeight = 60f
            ).apply {
                path(fill = SolidColor(onBackground)) {
                    moveTo(0f, 0f)
                    verticalLineToRelative(12f)
                    verticalLineToRelative(2f)
                    verticalLineToRelative(46f)
                    horizontalLineToRelative(60f)
                    verticalLineTo(14f)
                    verticalLineToRelative(-2f)
                    verticalLineTo(0f)
                    horizontalLineTo(0f)
                    close()
                    moveTo(20f, 39f)
                    horizontalLineToRelative(-3f)
                    verticalLineToRelative(8f)
                    curveToRelative(0f, 0.552f, -0.448f, 1f, -1f, 1f)
                    reflectiveCurveToRelative(-1f, -0.448f, -1f, -1f)
                    verticalLineToRelative(-8f)
                    horizontalLineTo(9f)
                    curveToRelative(-0.552f, 0f, -1f, -0.448f, -1f, -1f)
                    verticalLineTo(27f)
                    curveToRelative(0f, -0.552f, 0.448f, -1f, 1f, -1f)
                    reflectiveCurveToRelative(1f, 0.448f, 1f, 1f)
                    verticalLineToRelative(10f)
                    horizontalLineToRelative(5f)
                    verticalLineToRelative(-2f)
                    curveToRelative(0f, -0.552f, 0.448f, -1f, 1f, -1f)
                    reflectiveCurveToRelative(1f, 0.448f, 1f, 1f)
                    verticalLineToRelative(2f)
                    horizontalLineToRelative(3f)
                    curveToRelative(0.552f, 0f, 1f, 0.448f, 1f, 1f)
                    reflectiveCurveTo(20.552f, 39f, 20f, 39f)
                    close()
                    moveTo(36f, 41.5f)
                    curveToRelative(0f, 3.584f, -2.916f, 6.5f, -6.5f, 6.5f)
                    reflectiveCurveTo(23f, 45.084f, 23f, 41.5f)
                    verticalLineToRelative(-9f)
                    curveToRelative(0f, -3.584f, 2.916f, -6.5f, 6.5f, -6.5f)
                    reflectiveCurveToRelative(6.5f, 2.916f, 6.5f, 6.5f)
                    verticalLineTo(41.5f)
                    close()
                    moveTo(51f, 39f)
                    horizontalLineToRelative(-3f)
                    verticalLineToRelative(8f)
                    curveToRelative(0f, 0.552f, -0.448f, 1f, -1f, 1f)
                    reflectiveCurveToRelative(-1f, -0.448f, -1f, -1f)
                    verticalLineToRelative(-8f)
                    horizontalLineToRelative(-6f)
                    curveToRelative(-0.552f, 0f, -1f, -0.448f, -1f, -1f)
                    verticalLineTo(27f)
                    curveToRelative(0f, -0.552f, 0.448f, -1f, 1f, -1f)
                    reflectiveCurveToRelative(1f, 0.448f, 1f, 1f)
                    verticalLineToRelative(10f)
                    horizontalLineToRelative(5f)
                    verticalLineToRelative(-2f)
                    curveToRelative(0f, -0.552f, 0.448f, -1f, 1f, -1f)
                    reflectiveCurveToRelative(1f, 0.448f, 1f, 1f)
                    verticalLineToRelative(2f)
                    horizontalLineToRelative(3f)
                    curveToRelative(0.552f, 0f, 1f, 0.448f, 1f, 1f)
                    reflectiveCurveTo(51.552f, 39f, 51f, 39f)
                    close()
                    moveTo(2f, 12f)
                    verticalLineTo(2f)
                    horizontalLineToRelative(56f)
                    verticalLineToRelative(10f)
                    horizontalLineTo(2f)
                    close()
                }
                path(fill = SolidColor(onBackground)) {
                    moveTo(54.293f, 3.293f)
                    lineToRelative(-2.293f, 2.293f)
                    lineToRelative(-2.293f, -2.293f)
                    lineToRelative(-1.414f, 1.414f)
                    lineToRelative(2.293f, 2.293f)
                    lineToRelative(-2.293f, 2.293f)
                    lineToRelative(1.414f, 1.414f)
                    lineToRelative(2.293f, -2.293f)
                    lineToRelative(2.293f, 2.293f)
                    lineToRelative(1.414f, -1.414f)
                    lineToRelative(-2.293f, -2.293f)
                    lineToRelative(2.293f, -2.293f)
                    close()
                }
                path(fill = SolidColor(onBackground)) {
                    moveTo(3f, 3f)
                    horizontalLineToRelative(39f)
                    verticalLineToRelative(8f)
                    horizontalLineToRelative(-39f)
                    close()
                }
                path(fill = SolidColor(onBackground)) {
                    moveTo(29.5f, 28f)
                    curveToRelative(-2.481f, 0f, -4.5f, 2.019f, -4.5f, 4.5f)
                    verticalLineToRelative(9f)
                    curveToRelative(0f, 2.481f, 2.019f, 4.5f, 4.5f, 4.5f)
                    reflectiveCurveToRelative(4.5f, -2.019f, 4.5f, -4.5f)
                    verticalLineToRelative(-9f)
                    curveTo(34f, 30.019f, 31.981f, 28f, 29.5f, 28f)
                    close()
                }
            }
        }.build()
        return _NothingFoundImg!!
    }

@Suppress("ObjectPropertyName")
private var _NothingFoundImg: ImageVector? = null


@Preview
@Composable
private fun ShowImg() {
    MaterialTheme {
        Image(
            imageVector = NothingFoundImg,
            contentDescription = null,
        )
    }
}