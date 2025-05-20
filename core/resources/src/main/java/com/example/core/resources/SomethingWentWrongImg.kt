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

val SomethingWentWrongImg: ImageVector
    @Composable
    get() {
        val onBackground = MaterialTheme.colorScheme.onBackground

        _SomethingWentWrongImg = remember(onBackground) {
            ImageVector.Builder(
                name = "Error",
                defaultWidth = 240.dp,
                defaultHeight = 240.dp,
                viewportWidth = 512f,
                viewportHeight = 512f
            ).apply {
                path(fill = SolidColor(onBackground)) {
                    moveTo(502.75f, 185.06f)
                    curveToRelative(-5.11f, 0f, -9.25f, 4.14f, -9.25f, 9.25f)
                    verticalLineToRelative(37.01f)
                    curveToRelative(0f, 35.71f, -29.06f, 64.77f, -64.77f, 64.77f)
                    horizontalLineTo(92.53f)
                    verticalLineTo(92.53f)
                    horizontalLineToRelative(336.19f)
                    curveToRelative(35.72f, 0f, 64.77f, 29.06f, 64.77f, 64.77f)
                    curveToRelative(0f, 5.11f, 4.14f, 9.25f, 9.25f, 9.25f)
                    curveToRelative(5.11f, 0f, 9.25f, -4.14f, 9.25f, -9.25f)
                    curveToRelative(0f, -45.92f, -37.36f, -83.28f, -83.28f, -83.28f)
                    horizontalLineTo(92.53f)
                    verticalLineTo(58.6f)
                    curveToRelative(0f, -3.35f, -1.79f, -6.28f, -4.45f, -7.9f)
                    curveToRelative(2.83f, -4.95f, 4.45f, -10.67f, 4.45f, -16.77f)
                    curveTo(92.53f, 15.22f, 77.31f, 0f, 58.6f, 0f)
                    reflectiveCurveTo(24.67f, 15.22f, 24.67f, 33.93f)
                    curveToRelative(0f, 6.1f, 1.63f, 11.82f, 4.45f, 16.77f)
                    curveToRelative(-2.67f, 1.62f, -4.45f, 4.55f, -4.45f, 7.9f)
                    verticalLineTo(444.32f)
                    curveTo(10.81f, 445.86f, 0f, 457.64f, 0f, 471.9f)
                    verticalLineToRelative(30.84f)
                    curveTo(0f, 507.86f, 4.14f, 512f, 9.25f, 512f)
                    horizontalLineToRelative(98.7f)
                    curveToRelative(5.11f, 0f, 9.25f, -4.14f, 9.25f, -9.25f)
                    verticalLineToRelative(-30.84f)
                    curveToRelative(0f, -14.26f, -10.81f, -26.04f, -24.67f, -27.58f)
                    verticalLineTo(342.37f)
                    curveToRelative(0f, -5.11f, -4.14f, -9.25f, -9.25f, -9.25f)
                    curveToRelative(-5.11f, 0f, -9.25f, 4.14f, -9.25f, 9.25f)
                    verticalLineToRelative(101.78f)
                    horizontalLineTo(43.18f)
                    verticalLineTo(67.86f)
                    horizontalLineToRelative(30.84f)
                    verticalLineToRelative(237.49f)
                    verticalLineToRelative(0.01f)
                    curveToRelative(0f, 5.11f, 4.14f, 9.25f, 9.25f, 9.25f)
                    curveToRelative(0.04f, 0f, 0.08f, -0f, 0.12f, -0.01f)
                    horizontalLineToRelative(345.33f)
                    curveToRelative(45.92f, 0f, 83.28f, -37.36f, 83.28f, -83.28f)
                    verticalLineToRelative(-37.01f)
                    curveTo(512f, 189.2f, 507.86f, 185.06f, 502.75f, 185.06f)
                    close()
                    moveTo(89.45f, 462.65f)
                    curveToRelative(5.1f, 0f, 9.25f, 4.15f, 9.25f, 9.25f)
                    verticalLineToRelative(21.59f)
                    horizontalLineTo(18.51f)
                    verticalLineToRelative(-21.59f)
                    curveToRelative(0f, -5.1f, 4.15f, -9.25f, 9.25f, -9.25f)
                    horizontalLineTo(89.45f)
                    close()
                    moveTo(58.6f, 49.35f)
                    curveToRelative(-8.5f, 0f, -15.42f, -6.92f, -15.42f, -15.42f)
                    curveToRelative(0f, -8.5f, 6.92f, -15.42f, 15.42f, -15.42f)
                    reflectiveCurveToRelative(15.42f, 6.92f, 15.42f, 15.42f)
                    curveTo(74.02f, 42.43f, 67.11f, 49.35f, 58.6f, 49.35f)
                    close()
                }
                path(fill = SolidColor(onBackground)) {
                    moveTo(209.74f, 194.31f)
                    curveToRelative(0f, 39.12f, 31.82f, 70.94f, 70.94f, 70.94f)
                    curveToRelative(39.12f, 0f, 70.94f, -31.82f, 70.94f, -70.94f)
                    curveToRelative(0f, -39.12f, -31.82f, -70.94f, -70.94f, -70.94f)
                    curveTo(241.56f, 123.37f, 209.74f, 155.2f, 209.74f, 194.31f)
                    close()
                    moveTo(333.11f, 194.31f)
                    curveToRelative(0f, 28.91f, -23.52f, 52.43f, -52.43f, 52.43f)
                    curveToRelative(-28.91f, 0f, -52.43f, -23.52f, -52.43f, -52.43f)
                    curveToRelative(0f, -28.91f, 23.52f, -52.43f, 52.43f, -52.43f)
                    curveTo(309.59f, 141.88f, 333.11f, 165.4f, 333.11f, 194.31f)
                    close()
                }
                path(fill = SolidColor(onBackground)) {
                    moveTo(246.75f, 194.31f)
                    curveToRelative(0f, 5.11f, 4.14f, 9.25f, 9.25f, 9.25f)
                    horizontalLineToRelative(49.35f)
                    curveToRelative(5.11f, 0f, 9.25f, -4.14f, 9.25f, -9.25f)
                    curveToRelative(0f, -5.11f, -4.14f, -9.25f, -9.25f, -9.25f)
                    horizontalLineTo(256f)
                    curveTo(250.89f, 185.06f, 246.75f, 189.2f, 246.75f, 194.31f)
                    close()
                }
            }.build()
        }

        return _SomethingWentWrongImg!!
    }

@Suppress("ObjectPropertyName")
private var _SomethingWentWrongImg: ImageVector? = null


@Preview
@Composable
private fun ShowImg() {
    MaterialTheme {
        Image(
            imageVector = SomethingWentWrongImg,
            contentDescription = null,
        )
    }
}